import React from 'react';
import { RouteComponentProps } from 'react-router-dom';
import functions from '../core/functions';

class ComponentBase extends React.Component<RouteComponentProps<any>, {}> {
    constructor(props) {
        super(props);
        this.state = {
            bookingState : {
                id: this.props.userid
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

    componentWillMount() {
        this.updateBookingState();
    }

    updateBookingState = async () => {
        let that = this;

        const newBookingState = await functions.getBookingState(this.props.userid);
        that.setState({ bookingState: newBookingState });
    }
}

export default ComponentBase;
