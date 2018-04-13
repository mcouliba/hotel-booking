import React, { PropTypes } from 'react';
import { RouteComponentProps, withRouter } from 'react-router-dom';
import { Alert } from 'patternfly-react';
//import { Hotel } from '../models/hotel';
import HotelListView from '../components/ListView/HotelListView';
import constants from '../core/constants';
import functions from '../core/functions';

type State = {
  projectAlertVisible: boolean,
  stageAlertVisible: boolean
};

class HotelListPage extends React.Component<RouteComponentProps<any>, {}> {
    constructor(props: any) {
        super(props);
        this.state = {
          hotels: []
          ,bookingState : {
               id: this.props.credentials.username
               , state: this.props.location.pathname
               , search: {
                 city_name: '',
                 date_in: '',
                 date_out: ''
               }
           }
        };
      }
    dismissHotelListAlert = () => {
        this.setState({ hotelListAlertVisible: false });
    };

    async componentDidMount() {
        document.title = 'Hotel React App | HotelList';
        const response = await functions.restUrlCall(constants.get_bookingstate_url + "/" + this.state.bookingState.id);
        this.setState({ bookingState: response });
        await this.findHotelsByCity();
    }

    async componentDidUpdate() {
        const response = await functions.restUrlCall(constants.get_bookingstate_url + "/" + this.state.bookingState.id);
        this.setState({ bookingState: response });
    }


    async findHotelsByCity() {
        const response = await fetch(constants.find_hotels_url + this.props.credentials.username)
            .catch(e => console.log("Error when finding hotels"));
        const json = await response.json();
        await this.setState({hotels : json.content});
    };

  render() {
        const { search } = this.state.bookingState;

        return (
              <div className="container-fluid container-pf-nav-pf-vertical">
                      <div className="row">
                        <div className="col-md-12">
                          <div className="page-header">
                            <h1>Hotel List in {search.city_name}</h1>
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
                          <HotelListView hotels={ this.state.hotels }/>
                        </div>
                      </div>
                    </div>
            );
  }
}

export default withRouter(HotelListPage);
