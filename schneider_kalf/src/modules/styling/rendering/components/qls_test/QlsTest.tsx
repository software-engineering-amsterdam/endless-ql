import * as React from 'react';

export interface QlsTestProps {

}

export interface QlsTestState {

}

export class QlsTest extends React.Component<QlsTestProps, QlsTestState> {
  constructor(props: QlsTestProps) {
    super(props);

    this.state = {};
  }

  render() {
    return (
        <div>
          QLS TEST
        </div>
    );
  }
}