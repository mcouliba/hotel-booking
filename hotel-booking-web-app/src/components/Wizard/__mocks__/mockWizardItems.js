import React from 'react';

export const mockLoadingContents = () => (
  <div className="blank-slate-pf">
    <div className="spinner spinner-lg blank-slate-pf-icon" />
    <h3 className="blank-slate-pf-main-action">Loading Wizard</h3>
    <p className="blank-slate-pf-secondary-action">
      Lorem ipsum dolor sit amet, porta at suspendisse ac, ut wisi vivamus,
      lorem sociosqu eget nunc amet.{' '}
    </p>
  </div>
);

export const wizardRoomSelectedContents = (room) => {
    return (
        <div class="container-fluid container-cards-pf">
            <div class="row row-cards-pf">
                <div class="card-pf card-pf-view card-pf-view-select card-pf-view-single-select" id={room.id}>
                    <div class="card-pf-body">
                        <div class="card-pf-top-element">
                            <span class="glyphicon glyphicon-bed card-pf-icon-circle"></span>
                        </div>
                        <h2 class="card-pf-title text-center">
                            Room Number {room.room_number}
                        </h2>
                        <div class="card-pf-items text-center">
                            <div class="card-pf-item">
                                <span class="glyphicon glyphicon-euro"></span>
                                <span class="card-pf-item-text">{room.rate}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
};

export const mockWizardItems = [
  {
    step: 1,
    label: '1',
    title: 'Room Selected',
    subSteps: [
      {
        subStep: '1.1',
        label: '1.1',
        title: 'Room',
        contents: wizardRoomSelectedContents
      }
    ]
  },
  {
    step: 2,
    label: '2',
    title: 'Payment Details',
    subSteps: [
      {
        subStep: '2.1',
        label: '2.1',
        title: 'Details',
        contents: {
          label1: 'Credit Card Number',
          label2: 'Expiration Date'
        }
      }
    ]
  },
  {
    step: 3,
    label: '3',
    title: 'Summary',
    subSteps: [
      {
        subStep: '3.1',
        label: '3.1',
        title: 'Summary'
      }
    ]
  },
  {
      step: 4,
      label: '4',
      title: 'End',
      subSteps: [
        {
          subStep: '3.1',
          label: '3.1',
          title: 'End'
        }
      ]
    }
];

export const mockWizardFormContents = (label1, label2) => (
  <form className="form-horizontal">
    <div className="form-group required">
      <label className="col-sm-2 control-label">{label1}</label>
      <div className="col-sm-10">
        <input type="text" className="form-control" />
      </div>
    </div>
    <div className="form-group">
      <label className="col-sm-2 control-label">{label2}</label>
      <div className="col-sm-10">
        <textarea className="form-control" rows="2" />
      </div>
    </div>
  </form>
);
