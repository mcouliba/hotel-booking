import * as React from 'react';
import { withRouter } from 'react-router-dom';
import { Alert } from 'patternfly-react';
import PageBase from './PageBase';

const redhatHotelLogo = require('../redhat-hotel.png');

class HomePage extends PageBase {
  constructor(props: any) {
    super(props);

    this.state.alertVisible = false;
    this.state.dismissAlert = false;
  }


  componentDidUpdate() {
    if (!this.state.dismissAlert && !this.state.alertVisible && this.state.bookingState.state && this.state.bookingState.state !== "/") {
      this.setState({ alertVisible: true});
    }
  }

  dismissAlert = () => {
    this.setState({
        alertVisible: false
        , dismissAlert: true
    });
  };

  render() {

    return (
      <div className="container-fluid container-pf-nav-pf-vertical">
        <div className="page-header">
          <h2>Overview</h2>
        </div>
        <div className="App-body">
          {this.state.alertVisible && (
            <Alert type="info" onDismiss={this.dismissAlert}>
              <span><strong>You have a booking in progress!!</strong> <a href={this.state.bookingState.state} class="alert-link">Click to resume</a></span>
            </Alert>
          )}

          <span>
            <img style={{ height: 500 }} src={redhatHotelLogo} alt="logo" />
          </span>
        </div>
      </div>
    );
  }
}

export default withRouter(HomePage);
