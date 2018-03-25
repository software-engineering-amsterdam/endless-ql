import * as React from 'react';
import QlsForm from "../../../form/QlsForm";
import PageNode from "../../../form/nodes/containers/PageNode";
import { StyledFieldContainer } from "../styled_field_container/StyledFieldContainer";
import { QlsPage } from "../qls_page/QlsPage";
import { QlsPagination } from "../qls_pagination/QlsPagination";
import StyledFieldNode from "../../../form/StyledFieldNode";

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
    this.renderStyledField = this.renderStyledField.bind(this);
  }

  fieldIsVisible(field?: StyledFieldNode): boolean {
    const activePage = this.props.form.getActivePage();

    if (!field) {
      return false;
    }

    return field.isOnPage(activePage) && this.props.visibleFields.has(field.identifier);
  }

  renderStyledField(identifier: string) {
    const field = this.props.form.getField(identifier);

    if (!field || !this.fieldIsVisible(field)) {
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
              renderField={this.renderStyledField}
          />
        </div>
    );
  }
}