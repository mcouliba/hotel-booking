import * as React from 'react';
import { withRouter } from 'react-router-dom';
import HotelSearchForm from '../components/Forms/HotelSearchForm';
import functions from '../core/functions';
import { Redirect } from 'react-router';
import PageBase from './PageBase';

class HotelSearchPage extends PageBase {

      handleSubmitHotelSearch = async (event: Event) => {
        event.preventDefault();
        await functions.saveBookingState(this.state.bookingState, '/hotellist');
        this.setState({ redirect: true });
      };

        handleHotelSearchChange = (e, prop: string) => {
            const o = Object.assign({}, this.state.bookingState);
            o.search[prop] = e.currentTarget.value;
            this.setState({ bookingState: o });
        };

  render() {
        if (this.state.redirect) {
            return <Redirect to='/hotellist'/>;
        };

    return (
      <div className="container-fluid container-pf-nav-pf-vertical">
              <div className="row">
                <div className="col-md-12">
                  <div className="page-header">
                    <h1>Hotel Search</h1>
                  </div>
                </div>
              </div>

              <div className="row">
                <div className="col-md-12">
                  <br />
                  <HotelSearchForm
                    handleSubmit={this.handleSubmitHotelSearch}
                    handleChange={this.handleHotelSearchChange}
                    value={this.state.bookingState.search}
                  />
                </div>
              </div>
            </div>
    );
  }
}

export default withRouter(HotelSearchPage);
