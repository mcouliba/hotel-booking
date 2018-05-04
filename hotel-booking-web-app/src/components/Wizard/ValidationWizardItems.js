import React from 'react';

export const loadingContents = () => (
  <div className="blank-slate-pf">
    <div className="spinner spinner-lg blank-slate-pf-icon" />
    <h3 className="blank-slate-pf-main-action">Loading Wizard</h3>
    <p className="blank-slate-pf-secondary-action">
      Lorem ipsum dolor sit amet, porta at suspendisse ac, ut wisi vivamus,
      lorem sociosqu eget nunc amet.{' '}
    </p>
  </div>
);

export const wizardCustomerDetailsContents = (room, customer) => {

    return (
        <div className="container-fluid container-cards-pf">
            <div className="card-pf card-pf-view ">
                <div className="card-pf-body">
                    <div className="card-pf-top-element">
                        <span className="glyphicon glyphicon-bed card-pf-icon-circle"></span>
                    </div>
                    <div className="card-pf-items text-center">
                        <div className="card-pf-item">
                            <strong>#{room.room_number}</strong>
                        </div>
                        <div className="card-pf-item">
                            Price per night <strong>{room.rate}</strong>
                        </div>
                    </div>
                    <div className="card-pf-items text-center">
                        {room.living_area && (
                            <div className="card-pf-item">
                                living area
                            </div>
                        )}
                        {room.microwave && (
                            <div className="card-pf-item">
                                microwave
                            </div>
                        )}
                        {room.num_double_beds > 0 && (
                            <div className="card-pf-item">
                                double bed
                            </div>
                        )}
                        {room.num_king_beds > 0 && (
                            <div className="card-pf-item">
                                king bed
                            </div>
                        )}
                        {room.num_queen_beds > 0 && (
                            <div className="card-pf-item">
                                queen bed
                            </div>
                        )}
                        {room.microwave && (
                            <div className="card-pf-item">
                                pets
                            </div>
                        )}
                        {room.refrigerator && (
                            <div className="card-pf-item">
                                refrigerator
                            </div>
                        )}
                        {room.smoking && (
                            <div className="card-pf-item">
                                smoking
                            </div>
                        )}
                    </div>
                </div>
            </div>
            <div className="card-pf card-pf-view ">
                <div className="card-pf-body">
                    <div className="card-pf-top-element">
                        <span className="fa fa-user-circle card-pf-icon-circle"></span>
                    </div>
                    <div className="card-pf-items text-center">
                        <div className="card-pf-item">
                            <strong>{customer.fullname}</strong>
                        </div>
                        <div className="card-pf-item">
                            {customer.email}
                        </div>
                    </div>
                    <div className="card-pf-items text-center">
                        <div className="card-pf-item">
                            {customer.address}
                        </div>
                    </div>
                </div>
            </div>
            <div className="card-pf card-pf-view ">
                <div className="card-pf-body">
                    <div className="card-pf-top-element">
                        <span className="fa fa-trophy card-pf-icon-circle"></span>
                    </div>
                    <div className="card-pf-items text-center">
                        <div className="card-pf-item">
                            <strong>{customer.rewards_id}</strong>
                        </div>
                    </div>
                    <p className="card-pf-info text-center"><strong>Member since</strong> {customer.member_since}</p>
                </div>
            </div>
            <div className="card-pf card-pf-view ">
                <div className="card-pf-body">
                    <div className="card-pf-top-element">
                        <span className="fa fa-credit-card card-pf-icon-circle"></span>
                    </div>
                    <div className="card-pf-items text-center">
                        <div className="card-pf-item">
                            <strong>{customer.credit_card_number}</strong>
                        </div>
                        <div className="card-pf-item">
                            <strong>{customer.credit_card_type}</strong>
                        </div>
                    </div>
                    <p className="card-pf-info text-center"><strong>Expired by</strong> {customer.expiration_date}</p>
                </div>
            </div>
        </div>
    )
};

export const ValidationWizardItems = [
  {
    step: 1,
    label: '1',
    title: 'Informations',
    subSteps: [
      {
        subStep: '1.1',
        label: '1.1',
        title: 'Informations',
        contents: wizardCustomerDetailsContents
      }
    ]
  },
  {
    step: 2,
    label: '2',
    title: 'Summary',
    subSteps: [
      {
        subStep: '2.1',
        label: '2.1',
        title: 'Summary'
      }
    ]
  },
  {
      step: 3,
      label: '3',
      title: 'Booked!',
      subSteps: [
        {
          subStep: '3.1',
          label: '3.1',
          title: 'Booked!'
        }
      ]
    }
];
