import * as React from 'react';
import { ValidationWizardItems } from '../Wizard/ValidationWizardItems';
import { ValidationWizardModal } from '../Wizard/ValidationWizardModal';

class RoomListView extends React.Component {

  render() {
        const { rooms, customer } = this.props;

        return (
          <div className="list-group list-view-pf list-view-pf-view">

            {rooms.map((room,i) =>
                <div className="list-group-item" key={i}>

                  <div className="list-group-item-header">
                  <div className="list-view-pf-actions">
                    <ValidationWizardModal
                        steps={ValidationWizardItems}
                        room={ room }
                        handleBookingState={this.props.handleBookingState}
                        customer={ customer }
                    />
                  </div>
                  <div className="list-view-pf-main-info">
                    <div className="list-view-pf-left">
                      <span className="fa fa-bed list-view-pf-icon-sm"></span>
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
                                  <span className="fa fa-info-circle"></span>
                                  living area
                              </div>
                          )}
                          {room.microwave && (
                              <div className="list-view-pf-additional-info-item">
                                 <span className="fa fa-info-circle"></span>
                                  microwave
                              </div>
                          )}
                          {room.num_double_beds > 0 && (
                              <div className="list-view-pf-additional-info-item">
                                <span className="fa fa-info-circle"></span>
                                  double bed
                              </div>
                          )}
                          {room.num_king_beds > 0 && (
                              <div className="list-view-pf-additional-info-item">
                                <span className="fa fa-info-circle"></span>
                                  king bed
                              </div>
                          )}
                          {room.num_queen_beds > 0 && (
                              <div className="list-view-pf-additional-info-item">
                                <span className="fa fa-info-circle"></span>
                                  queen bed
                              </div>
                          )}
                          {room.microwave && (
                              <div className="list-view-pf-additional-info-item">
                                <span className="fa fa-info-circle"></span>
                                  pets
                              </div>
                          )}
                          {room.refrigerator && (
                              <div className="list-view-pf-additional-info-item">
                                <span className="fa fa-info-circle"></span>
                                  refrigerator
                              </div>
                          )}
                          {room.smoking && (
                              <div className="list-view-pf-additional-info-item">
                                <span className="fa fa-info-circle"></span>
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