import * as React from 'react';
import { BookingWizardItems } from '../Wizard/BookingWizardItems';
import { BookingModalWizard } from '../Wizard/BookingModalWizard';

class RoomListView extends React.Component {

  render() {
        const { rooms, customer } = this.props;

        return (
          <div className="list-group list-view-pf list-view-pf-view">

            {rooms.map((room,i) =>
                <div className="list-group-item" key={i}>

                  <div className="list-group-item-header">
                  <div className="list-view-pf-actions">
                    <BookingModalWizard
                        steps={BookingWizardItems}
                        room={ room }
                        handleBookingState={this.props.handleBookingState}
                        customer={ customer }
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
                      <div className="list-view-pf-additional-info">
                          {room.living_area && (
                              <div className="list-view-pf-additional-info-item">
                                  living area
                              </div>
                          )}
                          {room.microwave && (
                              <div className="list-view-pf-additional-info-item">
                                  microwave
                              </div>
                          )}
                          {room.num_double_beds > 0 && (
                              <div className="list-view-pf-additional-info-item">
                                  double bed
                              </div>
                          )}
                          {room.num_king_beds > 0 && (
                              <div className="list-view-pf-additional-info-item">
                                  king bed
                              </div>
                          )}
                          {room.num_queen_beds > 0 && (
                              <div className="list-view-pf-additional-info-item">
                                  queen bed
                              </div>
                          )}
                          {room.microwave && (
                              <div className="list-view-pf-additional-info-item">
                                  pets
                              </div>
                          )}
                          {room.refrigerator && (
                              <div className="list-view-pf-additional-info-item">
                                  refrigerator
                              </div>
                          )}
                          {room.smoking && (
                              <div className="list-view-pf-additional-info-item">
                                  smoking
                              </div>
                          )}
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