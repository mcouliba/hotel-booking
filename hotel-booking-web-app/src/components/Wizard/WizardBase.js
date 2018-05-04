import PropTypes from 'prop-types';
import ComponentBase from '../ComponentBase';
import { bindMethods } from '../../common/helpers';
import functions from "../../core/functions";

class WizardBase extends ComponentBase {
    constructor(props) {
        super(props);
        this.state.activeStepIndex = props.initialStepIndex || 0;
//        this.state.activeSubStepIndex = props.initialSubStepIndex || 0;
        this.state.rooms = [];

        bindMethods(this, [
          'onSidebarItemClick',
          'onStepClick',
          'onNextButtonClick',
          'onBackButtonClick',
          'onBookButtonClick'
        ]);
    }

  onBackButtonClick() {
    const { steps } = this.props;
    const { activeStepIndex } = this.state;

    if (activeStepIndex > 0) {
      this.setState(prevState => ({
        activeStepIndex: prevState.activeStepIndex - 1,
        activeSubStepIndex:
          steps[prevState.activeStepIndex - 1].subSteps.length - 1
      }));
    }
  }

  onNextButtonClick() {
    const { steps } = this.props;
    const { activeStepIndex } = this.state;

    if (activeStepIndex < steps.length - 1) {
      this.setState(prevState => ({
        activeStepIndex: prevState.activeStepIndex + 1,
        activeSubStepIndex: 0
      }));
    }
  }

    onBookButtonClick() {
        // Go to the last step
        this.setState({
            activeStepIndex: 2,
            activeSubStepIndex: 0
        });

        functions.confirmReservation(this.props.customer.id);
    }

  onSidebarItemClick(stepIndex, subStepIndex) {
    this.setState({
      activeStepIndex: stepIndex,
      activeSubStepIndex: subStepIndex
    });
  }
  onStepClick(stepIndex) {
    if (stepIndex === this.state.activeStepIndex) {
      return;
    }
    this.setState({
      activeStepIndex: stepIndex,
      activeSubStepIndex: 0
    });
  }
  render() {
    return false;
  }
}

WizardBase.propTypes = {
  /** Initial step index */
  initialStepIndex: PropTypes.number,
  /** Initial sub step index */
  initialSubStepIndex: PropTypes.number,
  /** Wizard steps */
  steps: PropTypes.array.isRequired,
  /** User ID */
  userid: PropTypes.string
};

WizardBase.defaultProps = {
  initialStepIndex: 0,
  initialSubStepIndex: 0
};

export default WizardBase;
