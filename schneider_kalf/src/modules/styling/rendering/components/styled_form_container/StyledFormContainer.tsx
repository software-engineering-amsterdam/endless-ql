import * as React from 'react';
import StyledForm from "../../../form/StyledForm";
import { FormComponent } from "../../../../../rendering/components/form_component/FormComponent";
import PageNode from "../../../form/nodes/containers/PageNode";
import { Pagination, PaginationItem, PaginationLink } from 'reactstrap';
import FieldNode from "../../../../../form/nodes/fields/FieldNode";
import { StyledFieldContainer } from "../styled_field_container/StyledFieldContainer";
import StyledFieldNode from "../../../form/StyledFieldNode";
import { getTypeString } from "../../../../../form/type_checking/type_assertions";

export interface StyledFormContainerProps {
  form: StyledForm;
  onChange: (identifier: string, value: any) => void;
  onChangePage: (nextPage: PageNode) => void;
  visibleFields: Set<string>;
}

export interface StyledFormContainerState {

}

export class StyledFormContainer extends React.Component<StyledFormContainerProps, StyledFormContainerState> {
  constructor(props: StyledFormContainerProps) {
    super(props);

    this.state = {};
    this.renderField = this.renderField.bind(this);
  }

  onChangePage(nextPage: PageNode, clickEvent: React.MouseEvent<HTMLElement>) {
    clickEvent.preventDefault();
    this.props.onChangePage(nextPage);
  }

  renderPaginationLinks() {
    const activePage = this.props.form.getActivePage();
    const pages: PageNode[] = this.props.form.getPages();

    return pages.map(page => {
      const isActive = typeof activePage !== 'undefined' && activePage.name === page.name;

      return (
          <PaginationItem active={isActive} key={page.name}>
            <PaginationLink onClick={event => this.onChangePage(page, event)} href="#">
              {page.name}
            </PaginationLink>
          </PaginationItem>
      );
    });
  }

  renderField(field: StyledFieldNode) {
    const activePage = this.props.form.getActivePage();

    if (!field.isOnPage(activePage)) {
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
    // TODO: Implement page navigation here

    return (
        <div>
          <h1>Styled form</h1>
          <FormComponent
              form={this.props.form}
              onChange={this.props.onChange}
              visibleFields={this.props.visibleFields}
              renderField={this.renderField}
          />
          <Pagination>
            {this.renderPaginationLinks()}
          </Pagination>
        </div>
    );
  }
}