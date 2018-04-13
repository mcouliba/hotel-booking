import * as React from 'react';
import { RouteComponentProps, withRouter } from 'react-router-dom';
import { Alert } from 'patternfly-react';
import constants from '../core/constants';
import functions from '../core/functions';
import { Redirect } from 'react-router'

const logo = require('../logo.svg');

interface State {
  alertVisible: boolean;
}
class HomePage extends React.Component<RouteComponentProps<any>, State> {
  constructor(props: any) {
    super(props);

    this.state = {
      alertVisible: true
      , bookingState : {
            state: this.props.location.pathname
      }
    };
  }
    async componentDidMount() {
        const response = await functions.restUrlCall(constants.get_bookingstate_url + "/" + this.props.credentials.username);
        this.setState({ bookingState: response });
    }

  dismissAlert = () => {
    this.setState({ alertVisible: false });
  };

  render() {
//    const { location } = this.props;
//    const { bookingState } = this.state;
//
//    if (bookingState.state !== location.pathname) {
//       return <Redirect to={bookingState.state}/>;
//     };

    return (
      <div className="container-fluid container-pf-nav-pf-vertical">
        <div className="page-header">
          <h2>Overview</h2>
        </div>
        <div className="App-body">
          {this.state.alertVisible && (
            <Alert type="success" onDismiss={this.dismissAlert}>
              <span>Well done! You've installed this demo correctly.</span>
            </Alert>
          )}
          <div className="App-intro">
            <img src={logo} className="App-logo" alt="logo" />
            <h2><font face="verdana">Welcome to Red Hat Hotel</font></h2>
            <h2><font face="verdana">Our door is always <strong>OPEN</strong></font></h2>
          </div>
          <p className="App-paragraph">
            To get started, edit <code>src/App.tsx</code> and save to reload.
          </p>
        </div>
      </div>
    );
  }
}

export default withRouter(HomePage);
