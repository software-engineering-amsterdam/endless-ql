import * as React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Input from "reactstrap/lib/Input";
import Form from "./form/Form";
import Alert from "reactstrap/lib/Alert";
import { getParserErrorMessage } from "./parsing/parsing_helpers";
import VisibleFieldsVisitor from "./form/evaluation/VisibleFieldsVisitor";
import { QlsParserPipeline, QlsParserResult } from "./modules/styling/parsing/QlsParserPipeline";
import StyledForm from "./modules/styling/form/StyledForm";
import PagedFormState from "./modules/styling/form/PagedFormState";
import { StyledFormContainer } from "./modules/styling/rendering/components/styled_form_container/StyledFormContainer";
import QuestionForm from "./form/QuestionForm";
import PageNode from "./modules/styling/form/nodes/containers/PageNode";
import Nav from "reactstrap/lib/Nav";
import NavItem from "reactstrap/lib/NavItem";
import NavLink from "reactstrap/lib/NavLink";
import * as classnames from "classnames";
import TabContent from "reactstrap/lib/TabContent";
import TabPane from "reactstrap/lib/TabPane";
import { Label } from 'reactstrap';
import { QlParserPipeline, QlParserResult } from "./parsing/QlParserPipeline";
import { FormComponent } from "./rendering/components/form_component/FormComponent";
import Button from "reactstrap/lib/Button";

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
      activeTab: "ql",
      form: null,
      parserError: null
    };

    this.onChangeAnswer = this.onChangeAnswer.bind(this);
    this.onChangePage = this.onChangePage.bind(this);
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
    const parseResult: any = this.getParseResult(qlSource, (qlsEnabled) ? qlsSource : "");

    const form = new QuestionForm(parseResult.node, this.getFormState());

    this.setState({
      form: (typeof parseResult.styleNode !== 'undefined') ? new StyledForm(form, parseResult.styleNode) : form,
      parserError: null,
      qlInput: qlSource,
      qlsInput: qlsSource,
      qlsEnabled: qlsEnabled
    });
  }

  getParseResult(qlSource: string, qlsSource: string): QlParserResult {
    const qlsEnabled = qlsSource.length > 0;

    if (qlsEnabled) {
      return (new QlsParserPipeline(qlSource, qlsSource)).run();
    }

    return (new QlParserPipeline(qlSource)).run()[0];
  }

  getFormState() {
    if (!this.state.form) {
      return new PagedFormState();
    }

    return this.state.form.getState();
  }

  onChangeAnswer(identifier: string, value: any) {
    if (!this.state.form) {
      return;
    }

    this.setState({
      form: this.state.form.setAnswer(identifier, value)
    });
  }

  onChangePage(nextPage: PageNode) {
    if (!this.state.form) {
      return;
    }

    this.setState({
      form: this.state.form.setActivePage(nextPage)
    });
  }

  renderErrorMessage() {
    if (!this.state.parserError) {
      return null;
    }

    return (
        <Alert color="danger">
          {getParserErrorMessage(this.state.parserError)}
        </Alert>
    );
  }

  renderForm() {
    if (!this.state.form) {
      return (
          <span>Form not yet parsed</span>
      );
    }

    if (this.state.qlsEnabled && this.state.form instanceof StyledForm) {
      return (
          <StyledFormContainer
              onChange={this.onChangeAnswer}
              onChangePage={this.onChangePage}
              form={this.state.form}
              visibleFields={VisibleFieldsVisitor.run(this.state.form)}
          />
      );
    }

    return (
        <FormComponent
            onChange={this.onChangeAnswer}
            form={this.state.form}
            visibleFields={VisibleFieldsVisitor.run(this.state.form)}
        />
    );
  }

  changeActiveTab(nextTab: string) {
    this.setState({
      activeTab: nextTab
    });
  }

  onExportState() {
    if (!this.state.form) {
      return;
    }

    const json: string = this.state.form.getState().toJson();
    require("downloadjs")(json, `${this.state.form.getName()}_${Math.round(Date.now())}`, "application/json");
  }

  render() {
    return (
        <div className="app container">
          <h1>NEWSKQL</h1>
          <div className="row ql-sample-output">
            <div className="col-md-6">
              <Nav tabs={true}>
                <NavItem>
                  <NavLink
                      className={classnames({active: this.state.activeTab === 'ql'})}
                      onClick={() => this.changeActiveTab("ql")}
                  >
                    QL
                  </NavLink>
                </NavItem>
                <NavItem>
                  <NavLink
                      className={classnames({active: this.state.activeTab === 'qls'})}
                      onClick={() => this.changeActiveTab("qls")}
                  >
                    QLS ({(this.state.qlsEnabled) ? 'enabled' : 'disabled'})
                  </NavLink>
                </NavItem>
              </Nav>
              <TabContent activeTab={this.state.activeTab}>
                <TabPane tabId="ql">
                  <Input
                      valid={!this.state.parserError}
                      type="textarea"
                      value={this.state.qlInput}
                      onChange={e => this.onChangeQlSource(e.target.value)}
                      name="ql_input"
                  />
                </TabPane>
                <TabPane tabId="qls">
                  <Label check={true}>
                    <Input
                        type="checkbox"
                        checked={this.state.qlsEnabled}
                        onChange={e => this.toggleQls(e.target.checked)}
                    />{' '}
                    Enable QLS
                  </Label>
                  <Input
                      valid={!this.state.parserError}
                      type="textarea"
                      disabled={!this.state.qlsEnabled}
                      value={this.state.qlsInput}
                      onChange={e => this.onChangeQlsSource(e.target.value)}
                      name="ql_input"
                  /> </TabPane>
              </TabContent>

            </div>
            <div className="col-md-6">
              {this.renderErrorMessage()}
              {this.renderForm()}
              <hr/>
              <div className="state-output-container">
                <div className="row">
                  <div className="col-md-8">
                    <h2>State</h2>
                  </div>
                  <div className="col-md-4">
                    <Button onClick={() => this.onExportState()}>Export state</Button>
                  </div>
                </div>
                <Input
                    type="textarea"
                    readOnly={true}
                    value={this.getFormState().toString()}
                />
              </div>

            </div>
          </div>
        </div>
    );
  }
}

export default App;