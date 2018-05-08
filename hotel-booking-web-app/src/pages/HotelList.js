import React from 'react';
import { withRouter } from 'react-router-dom';
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

    componentWillMount() {
        this.updateBookingState();
        this.findHotelsByCity();
    }

    findHotelsByCity() {
        fetch(constants.find_hotels_url + this.props.credentials.customerid)
            .then(response => response.json())
            .then(data => {
                this.setState({hotels : data.content})
            })
            .catch(e => console.log("Error when finding hotels"));
    };

    handleSubmit = async (event: Event, hotel: any) => {
        event.preventDefault();
        const newBookingState = Object.assign({}, this.state.bookingState);
        newBookingState.selection.hotel = hotel;
        Promise.resolve(functions.saveBookingState(newBookingState, '/roomlist'))
          .then(() => {
            this.setState({ redirect: true });
          });
    };

  render() {
        if (this.state.redirect) {
            return <Redirect to='/roomlist'/>;
        };

        const { search } = this.state.bookingState;

        return (
            <div>
                <div className="page-header">
                    <h1>Hotels in <strong>{search.city_name}</strong>
                    <br/>from <strong>{search.date_in}</strong> to <strong>{search.date_out}</strong></h1>
                </div>

                <br />
                <HotelListView
                hotels={ this.state.hotels }
                handleSubmit={this.handleSubmit}
                />
            </div>
        );
  }
}

export default withRouter(HotelListPage);
