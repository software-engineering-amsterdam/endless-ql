import * as React from 'react';
import QlsForm from "../../../form/QlsForm";
import PageNode from "../../../form/nodes/containers/PageNode";
import { StyledFieldContainer } from "../styled_field_container/StyledFieldContainer";
import { QlsPage } from "../qls_page/QlsPage";
import { QlsPagination } from "../qls_pagination/QlsPagination";

export interface QlsFormComponentProps {
  form: QlsForm;
  onChange: (identifier: string, value: any) => void;
  onChangePage: (nextPage: PageNode) => void;
  visibleFields: Set<string>;
}

export interface QlsFormComponentState {
}

export class QlsFormComponent extends React.Component<QlsFormComponentProps, QlsFormComponentState> {
  constructor(props: QlsFormComponentProps) {
    super(props);

    this.state = {};
    this.renderField = this.renderField.bind(this);
  }

  renderField(identifier: string) {
    const field = this.props.form.getField(identifier);
    const activePage = this.props.form.getActivePage();

    if (!field || !field.isOnPage(activePage) || !this.props.visibleFields.has(field.identifier)) {
      return null;
    }

    return (
        <StyledFieldContainer
            onChange={value => this.props.onChange(field.identifier, value)}
            key={field.identifier}
            field={field}
            value={this.props.form.getState().get(field.identifier)}
        />
    );
  }

  render() {
    return (
        <div className="form-container--styled">
          <QlsPagination
              activePage={this.props.form.getActivePage()}
              pages={this.props.form.getPages()}
              onChangePage={this.props.onChangePage}
          />
          <QlsPage
              page={this.props.form.getActivePage()}
              renderField={this.renderField}
          />
        </div>
    );
  }
}