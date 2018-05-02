import * as React from 'react';
import { mockWizardItems } from '../Wizard/__mocks__/mockWizardItems';
import { BookingModalWizard } from '../Wizard/BookingModalWizard';

class RoomListView extends React.Component {

  render() {
        const { rooms } = this.props;

        return (
          <div className="list-group list-view-pf list-view-pf-view">

            {rooms.map((room,i) =>
                <div className="list-group-item" key={i}>

                  <div className="list-group-item-header">
                  <div className="list-view-pf-actions">
                    <BookingModalWizard
                        steps={mockWizardItems}
                        room={room}
                        handleBookingState={this.props.handleBookingState}
                        userid={this.props.userid}
                    />
                  </div>
                  <div className="list-view-pf-main-info">
                    <div className="list-view-pf-left">
                      <span className="glyphicon glyphicon-bed list-view-pf-icon-sm"></span>
                    </div>
                    <div className="list-view-pf-body">
                      <div className="list-view-pf-description">
                        <div className="list-group-item-heading">
                          Room Number {room.hotelId}/{ room.room_number }
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
          )}
          </div>
        );
  }

}

export default RoomListView;