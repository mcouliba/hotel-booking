import * as React from 'react';
import { withRouter } from 'react-router-dom';
import HotelSearchForm from '../components/Forms/HotelSearchForm';
import functions from '../core/functions';
import { Redirect } from 'react-router';
import PageBase from './PageBase';

class HotelSearchPage extends PageBase {

  handleSubmitHotelSearch = (event: Event) => {
    event.preventDefault();

    Promise.resolve(functions.saveBookingState(this.state.bookingState, '/hotellist'))
      .then(() => {
        this.setState({ redirect: true });
      });
  };

    handleHotelSearchChange = (e, prop: string) => {
        const o = Object.assign({}, this.state.bookingState);
        o.search[prop] = e.currentTarget.value;
        this.setState({ bookingState: o });
    };

  componentWillMount() {
      this.updateBookingState();
  }

  render() {
        if (this.state.redirect) {
            return <Redirect to='/hotellist'/>;
        };

    return (
      <div>
        <div>
          <div className="page-header">
            <h1>Hotel Search</h1>
          </div>
        </div>

        <div>
          <br />
          <HotelSearchForm
            handleSubmit={this.handleSubmitHotelSearch}
            handleChange={this.handleHotelSearchChange}
            value={this.state.bookingState.search}
          />
        </div>
        </div>
    );
  }
}

export default withRouter(HotelSearchPage);
