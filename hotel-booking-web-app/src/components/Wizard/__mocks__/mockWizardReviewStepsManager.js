import React from 'react';
import PropTypes from 'prop-types';
import { bindMethods } from '../../../common/helpers';
import constants from '../../../core/constants';
import functions from '../../../core/functions';

class MockWizardReviewStepsManager extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      steps: [...props.steps]
      ,bookingState : {
        id: ''
        , state: ''
        , search: {
          city_name: '',
          date_in: '',
          date_out: ''
        }, selection: {
          hotel: {name : ''}
          , room: { room_number : ''}
        }
        }
    };
    bindMethods(this, ['stepClicked', 'subStepClicked']);
  }
  stepClicked(e, stepIndex) {
    e.preventDefault();
    const updated = [...this.state.steps];
    updated[stepIndex].collapsed = !updated[stepIndex].collapsed;
    this.setState({
      steps: updated
    });
  }
  subStepClicked(e, stepIndex, subStepIndex) {
    e.preventDefault();
    const updated = [...this.state.steps];
    updated[stepIndex].subSteps[subStepIndex].collapsed = !updated[stepIndex]
      .subSteps[subStepIndex].collapsed;
    this.setState({
      steps: updated
    });
  }

  async componentWillMount() {
      const response = await functions.restUrlCall(constants.get_bookingstate_url + "/" + this.props.userid);
      this.setState({ bookingState: response });
  }


  render() {

    return (
        <div>
            Hello Madam/Sir,<br/><br/>
            You are going to book the room <strong>{this.state.bookingState.selection.room.room_number}</strong> in the hotel <strong>{this.state.bookingState.selection.hotel.name}</strong>.<br/><br/>

            The total price for this booking is € XXXX.<br/><br/>
            Thank you<br/>
            Red Hat Hotel Team
        </div>
    );
  }
}
MockWizardReviewStepsManager.propTypes = {
  /** Wizard steps */
  steps: PropTypes.array.isRequired
};
export default MockWizardReviewStepsManager;
