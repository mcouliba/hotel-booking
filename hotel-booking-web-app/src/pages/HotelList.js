import React from 'react';
import { withRouter } from 'react-router-dom';
import { Alert } from 'patternfly-react';
import { Redirect } from 'react-router';
import HotelListView from '../components/ListView/HotelListView';
import constants from '../core/constants';
import functions from '../core/functions';
import PageBase from './PageBase';

class HotelListPage extends PageBase {
    constructor(props: any) {
        super(props);
        this.state.hotels = [];
    }

    async componentWillMount() {
        await this.findHotelsByCity();
    }

    async findHotelsByCity() {
        const response = await fetch(constants.find_hotels_url + this.props.credentials.customerid)
            .catch(e => console.log("Error when finding hotels"));
        const json = await response.json();
        await this.setState({hotels : json.content});
    };

    handleSubmit = async (event: Event, hotel: any) => {
        event.preventDefault();
        const o = Object.assign({}, this.state.bookingState);
        o.selection.hotel = hotel;
        await this.setState({ bookingState: o });
        await functions.saveBookingState(this.state.bookingState, '/roomlist');
        await this.setState({ redirect: true });
    };

  render() {
        if (this.state.redirect) {
            return <Redirect to='/roomlist'/>;
        };

        const { search } = this.state.bookingState;

        return (
              <div className="container-fluid container-pf-nav-pf-vertical">
                      <div className="row">
                        <div className="col-md-12">
                          <div className="page-header">
                            <h1>Hotels in <strong>{search.city_name}</strong>
                            <br/>from <strong>{search.date_in}</strong> to <strong>{search.date_out}</strong></h1>
                          </div>
                        </div>
                      </div>

                      <div className="row">
                        <div className="col-md-12">
                          <br />
                          {this.state.hotelListAlertVisible && (
                            <Alert type="warning" onDismiss={this.dismissHotelListAlert}>
                              <strong>Warning</strong> Fill with something
                            </Alert>
                          )}
                          <HotelListView
                            hotels={ this.state.hotels }
                            handleSubmit={this.handleSubmit}
                          />
                        </div>
                      </div>
                    </div>
            );
  }
}

export default withRouter(HotelListPage);
