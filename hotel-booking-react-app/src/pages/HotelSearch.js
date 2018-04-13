import * as React from 'react';
import { RouteComponentProps, withRouter } from 'react-router-dom';
import { Alert } from 'patternfly-react';
import HotelSearchForm from '../components/Forms/HotelSearchForm';
import BrowserHistory from 'react-router';
import constants from '../core/constants';
import functions from '../core/functions';
import { Redirect } from 'react-router';

type State = {
  projectAlertVisible: boolean,
  stageAlertVisible: boolean
};

class HotelSearchPage extends React.Component<RouteComponentProps<any>, {}> {
    constructor(props: any) {
        super(props);
        this.state = {
          goNext: false,
          hotelSearchAlertVisible: false,
            bookingState : {
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

    async componentDidMount() {
        document.title = 'Hotel React App | HotelSearch';
        const response = await functions.restUrlCall(constants.get_bookingstate_url + "/" + this.state.bookingState.id);
        this.setState({ bookingState: response });
    }

    saveState = () => {
        fetch(constants.set_search_url, {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(this.state.bookingState)
        })
        .catch(e => console.log("Error when saving Search State"));
    };

      handleSubmitHotelSearch = (event: Event) => {
        event.preventDefault();
        this.setState({ hotelSearchAlertVisible: true });
        this.saveState();
        this.setState({ redirect: true });
      };

        handleHotelSearchChange = (e, prop: string) => {
            const o = Object.assign({}, this.state.bookingState);
            o.search[prop] = e.currentTarget.value;
            this.setState({ bookingState: o });
        };

      dismissHotelSearchAlert = () => {
        this.setState({ hotelSearchAlertVisible: false });
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
