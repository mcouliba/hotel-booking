import React from 'react';
import PropTypes from 'prop-types';

const ValidationWizardReviewStepsManager = props => {
    if (props.bookingState) {
        const {search, selection } = props.bookingState;
        var date_in = new Date(search.date_in);
        var date_out = new Date(search.date_out);
        var timeDiff = Math.abs(date_in - date_out);
        var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

        var totalPrice = diffDays * selection.room.rate;

        return (
            <div>
                <div className="panel panel-default">
                  <div className="panel-heading">
                    <h3 className="panel-title">Stay Duration</h3>
                  </div>
                  <div className="panel-body">
                    You are going to book for <strong>{diffDays} days</strong> (from {search.date_in} to {search.date_out})
                  </div>
                </div>
                <div className="panel panel-default">
                  <div className="panel-heading">
                    <h3 className="panel-title">Hotel</h3>
                  </div>
                  <div className="panel-body">
                    You will stay at the hotel <strong>{selection.hotel.name}</strong>.
                  </div>
                </div>
                <div className="panel panel-default">
                  <div className="panel-heading">
                    <h3 className="panel-title">Room</h3>
                  </div>
                  <div className="panel-body">
                    You have chosen the room <strong>{selection.room.room_number}</strong>
                  </div>
                </div>
                <div className="panel panel-default">
                  <div className="panel-heading">
                    <h3 className="panel-title">Price</h3>
                  </div>
                  <div className="panel-body">
                    The total price is <strong>{totalPrice}</strong> ({diffDays} x {selection.room.rate})
                  </div>
                </div>

            </div>
        );
    }
}
ValidationWizardReviewStepsManager.propTypes = {
  bookingState: PropTypes.any
};
export default ValidationWizardReviewStepsManager;
