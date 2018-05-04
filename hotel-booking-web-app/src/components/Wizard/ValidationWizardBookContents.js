import React from 'react';
import PropTypes from 'prop-types';

class ValidationWizardBookContents extends React.Component {
  constructor() {
    super();
    this.state = { booking: true };
  }
  componentWillReceiveProps(nextProps) {
    const { active } = this.props;
    if (!nextProps.active) {
      this.setState({ booking: true });
    } else if (!active && nextProps.active) {
      setTimeout(() => {
        this.setState({ booking: false });
      }, 3000);
    }
  }
  render() {
    if (this.state.booking) {
      return (
        <div className="wizard-pf-process blank-slate-pf">
          <div className="spinner spinner-lg blank-slate-pf-icon" />
          <h3 className="blank-slate-pf-main-action">Booking in progress</h3>
        </div>
      );
    }
    return (
      <div className="wizard-pf-complete blank-slate-pf">
        <div className="wizard-pf-success-icon">
          <span className="glyphicon glyphicon-ok-circle" />
        </div>
        <h3 className="blank-slate-pf-main-action">
          Booking was successful
        </h3>
      </div>
    );
  }
}
ValidationWizardBookContents.propTypes = {
  active: PropTypes.bool.isRequired
};
export default ValidationWizardBookContents;
