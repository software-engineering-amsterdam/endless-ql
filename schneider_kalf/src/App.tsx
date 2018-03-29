import * as React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Form from "./form/StatefulForm";
import PagedFormState from "./modules/styling/form/PagedFormState";
import PageNode from "./modules/styling/form/nodes/containers/PageNode";
import { ModuleTabNavigation } from "./rendering/components/app_module_tabs/ModuleTabNavigation";
import { ModuleTabsContent } from "./rendering/components/app_module_tabs/ModuleTabsContent";
import { AppFormStateOutput } from "./rendering/components/app_form_state_output/FormStateOutput";
import { AppErrorMessage } from "./rendering/components/app_error_message/AppErrorMessage";
import { AppFormContainer } from './rendering/components/app_form_container/AppFormContainer';
import constants from "./config/constants";
import SourceInputs from "./form/source/SourceInputs";
import { makeStatefulForm } from "./app_form_helpers";
import QlForm from "./form/QlForm";
import QlsForm from "./modules/styling/form/QlsForm";

export interface AppComponentProps {
}

export interface AppComponentState {
  qlInput: string;
  qlsInput: string;
  form: QlsForm | QlForm | Form | any;
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
    this.onResetFormState = this.onResetFormState.bind(this);
  }

  componentDidMount() {
    this.updateForm(this.state.qlInput, this.state.qlsInput, this.state.qlsEnabled);
  }

  updateForm(qlSource: string, qlsSource: string, qlsEnabled: boolean) {
    const inputs = SourceInputs.makeFromStrings(qlSource, qlsSource, qlsEnabled);

    try {
      this.tryToUpdateForm(inputs);
    } catch (error) {
      this.setState({
        parserError: error,
        qlInput: qlSource,
        qlsInput: qlsSource
      });
    }
  }

  tryToUpdateForm(inputs: SourceInputs) {
    const form = makeStatefulForm(inputs, this.getFormState());

    this.setState({
      form: form,
      parserError: null,
      qlInput: inputs.getQlSource().toString(),
      qlsInput: inputs.getQlsSource().toString(),
      qlsEnabled: inputs.qlsIsEnabled()
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

  onChangeQlSource(text: string) {
    this.updateForm(text, this.state.qlsInput, this.state.qlsEnabled);
  }

  onChangeQlsSource(text: string) {
    this.updateForm(this.state.qlInput, text, this.state.qlsEnabled);
  }

  onResetFormState() {
    const newState = this.getFormState().instantiate(new Map());
    this.setState({
      form: this.state.form.setState(newState)
    });
  }

  toggleQls(qlsEnabled: boolean) {
    this.updateForm(this.state.qlInput, this.state.qlsInput, qlsEnabled);
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
                  qlsEnabled={this.state.qlsEnabled && this.state.qlsInput.trim().length > 0}
                  onChangeAnswer={this.onChangeAnswer}
                  onChangePage={this.onChangePage}
              />
              <hr/>
              <AppFormStateOutput
                  form={this.state.form}
                  onReset={this.onResetFormState}
              />
            </div>
          </div>
        </div>
    );
  }
}

export default App;