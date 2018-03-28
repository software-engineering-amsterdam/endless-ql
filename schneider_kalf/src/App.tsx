import * as React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Form from "./form/Form";
import { QlsParserPipeline, QlsParserResult } from "./modules/styling/parsing/QlsParserPipeline";
import QlsForm from "./modules/styling/form/QlsForm";
import PagedFormState from "./modules/styling/form/PagedFormState";
import QlForm from "./form/QlForm";
import PageNode from "./modules/styling/form/nodes/containers/PageNode";
import { QlParserResult } from "./parsing/QlParserPipeline";
import { ModuleTabNavigation } from "./rendering/components/app_module_tabs/ModuleTabNavigation";
import { ModuleTabsContent } from "./rendering/components/app_module_tabs/ModuleTabsContent";
import { AppFormStateOutput } from "./rendering/components/app_form_state_output/FormStateOutput";
import { AppErrorMessage } from "./rendering/components/app_error_message/AppErrorMessage";
import { AppFormContainer } from './rendering/components/app_form_container/AppFormContainer';
import { runParserPipeline } from "./parsing/parsing_helpers";
import constants from "./config/constants";

export interface AppComponentProps {
}

export interface AppComponentState {
  qlInput: string;
  qlsInput: string;
  form: Form | any | null;
  parserError: Error | null;
  qlsEnabled: boolean;
  activeTab: string;
}

class App extends React.Component<AppComponentProps, AppComponentState> {
  constructor(props: AppComponentProps) {
    super(props);

    this.state = {
      qlInput: require("!raw-loader!./mock/sample.ql.txt"),
      qlsInput: require("!raw-loader!./modules/styling/mock/sample.qls.txt"),
      qlsEnabled: true,
      activeTab: constants.APP_MODULE_TABS.QL,
      form: null,
      parserError: null
    };

    this.onChangeAnswer = this.onChangeAnswer.bind(this);
    this.onChangePage = this.onChangePage.bind(this);
    this.onChangeTab = this.onChangeTab.bind(this);
    this.onChangeQlSource = this.onChangeQlSource.bind(this);
    this.onChangeQlsSource = this.onChangeQlsSource.bind(this);
    this.toggleQls = this.toggleQls.bind(this);
  }

  componentDidMount() {
    this.updateForm(this.state.qlInput, this.state.qlsInput, this.state.qlsEnabled);
  }

  onChangeQlSource(text: string) {
    this.updateForm(text, this.state.qlsInput, this.state.qlsEnabled);
  }

  onChangeQlsSource(text: string) {
    this.updateForm(this.state.qlInput, text, this.state.qlsEnabled);
  }

  toggleQls(qlsEnabled: boolean) {
    this.updateForm(this.state.qlInput, this.state.qlsInput, qlsEnabled);
  }

  updateForm(qlSource: string, qlsSource: string, qlsEnabled: boolean) {
    try {
      this.tryToUpdateForm(qlSource, qlsSource, qlsEnabled);
    } catch (error) {
      this.setState({
        parserError: error,
        qlInput: qlSource,
        qlsInput: qlsSource
      });
    }
  }

  tryToUpdateForm(qlSource: string, qlsSource: string, qlsEnabled: boolean) {
    const parseResult: QlParserResult | QlsParserResult | any = runParserPipeline(qlSource, qlsSource, qlsEnabled);

    let form: Form = new QlForm(parseResult.node, this.getFormState());

    // TODO: Maybe put both pipelines in different functions
    if (typeof parseResult.styleNode !== 'undefined') {
      form = new QlsForm(form, parseResult.styleNode);
    }

    this.setState({
      form: form,
      parserError: null,
      qlInput: qlSource,
      qlsInput: qlsSource,
      qlsEnabled: qlsEnabled
    });
  }

  getFormState() {
    if (!this.state.form) {
      return new PagedFormState();
    }

    return this.state.form.getState();
  }

  onChangeAnswer(identifier: string, value: any) {
    this.setState({
      form: this.state.form.setAnswer(identifier, value)
    });
  }

  onChangePage(nextPage: PageNode) {
    this.setState({
      form: this.state.form.setActivePage(nextPage)
    });
  }

  onChangeTab(nextTab: string) {
    this.setState({
      activeTab: nextTab
    });
  }

  render() {
    return (
        <div className="app container">
          <h1>NEWSKQL</h1>
          <div className="row ql-sample-output">
            <div className="col-md-6">
              <ModuleTabNavigation
                  activeTab={this.state.activeTab}
                  onChange={this.onChangeTab}
                  qlsEnabled={this.state.qlsEnabled}
              />
              <ModuleTabsContent
                  activeTab={this.state.activeTab}
                  onChangeQl={this.onChangeQlSource}
                  onChangeQls={this.onChangeQlsSource}
                  qlInput={this.state.qlInput}
                  qlsInput={this.state.qlsInput}
                  qlsEnabled={this.state.qlsEnabled}
                  toggleQls={this.toggleQls}
                  error={this.state.parserError}
              />
            </div>
            <div className="col-md-6">
              <AppErrorMessage
                  error={this.state.parserError}
              />
              <AppFormContainer
                  form={this.state.form}
                  qlsEnabled={this.state.qlsEnabled}
                  onChangeAnswer={this.onChangeAnswer}
                  onChangePage={this.onChangePage}
              />
              <hr/>
              <AppFormStateOutput
                  form={this.state.form}
              />
            </div>
          </div>
        </div>
    );
  }
}

export default App;