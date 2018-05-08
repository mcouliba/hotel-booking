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

   componentWillMount() {
         this.updateBookingState();
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
      <div>
        <div className="page-header">
          <h2>Overview</h2>
        </div>
        <div className="App-body">
          {this.state.alertVisible && (
            <Alert type="info" onDismiss={this.dismissAlert}>
              <span><strong>You have a booking in progress!!</strong> <a href={this.state.bookingState.state} class="alert-link">Click to resume</a></span>
            </Alert>
          )}

          <div className="App-intro">
            <img src={redhatHotelLogo} className="App-logo" alt="logo" />
            <h2><strong>Welcome to Red Hat Hotel</strong></h2>
          </div>
            <p className="App-paragraph">
            </p>
        </div>
       </div>
    );
  }
}

export default withRouter(HomePage);
