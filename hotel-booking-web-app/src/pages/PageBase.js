import React from 'react';
import { RouteComponentProps } from 'react-router-dom';
import functions from '../core/functions';

class PageBase extends React.Component<RouteComponentProps<any>, {}> {
    constructor(props) {
        super(props);
        this.state = {
            bookingState : {
                id: this.props.credentials.customerid
                , state: '/'
                , search: {
                    city_name: '',
                    date_in: '',
                    date_out: ''
                }, selection: {
                    hotel: {}
                    , room: {}
                }
            }
        };
    }

    componentDidMount() {
        document.title = 'Red Hat Hotel Booking App';
    }

    updateBookingState = async () => {
        let that = this;

        const newBookingState = await functions.getBookingState(this.props.credentials.customerid);
        that.setState({ bookingState: newBookingState });
    }
}

export default PageBase;
