import React from 'react';
import { RouteComponentProps } from 'react-router-dom';
import constants from '../core/constants';
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

    async componentDidMount() {
        document.title = 'Red Hat Hotel';
        const response = await functions.restUrlCall(constants.get_bookingstate_url + "/" + this.props.credentials.customerid);
        this.setState({ bookingState: response });
    }
}

export default PageBase;
