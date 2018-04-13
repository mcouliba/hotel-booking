import React from 'react';
import { FormGroup, ControlLabel, Radio, HelpBlock, DropdownButton, MenuItem } from 'patternfly-react';
import { EmptyState } from '../../EmptyState/EmptyState';

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

export const wizardRoomContents = (rooms) => {
    if (rooms && rooms.length > 0) {
        return (
            <DropdownButton
              bsStyle="default"
              title="default"
              id="dropdown-example"
            >
                {rooms.map((room,i) =>
                    <MenuItem eventKey="{i}">{room.type} - {room.maxCapacity} guest(s)</MenuItem>
               )}
            </DropdownButton>
        )
    } else {
        return (
            <EmptyState title="No Rooms" />
        )
    }
};

export const mockWizardItems = [
  {
    step: 1,
    label: '1',
    title: 'Options',
    subSteps: [
      {
        subStep: '1.1',
        label: '1.1',
        title: 'Room',
        contents: {
          label1: 'XXX',
          label2: 'YYY'
        },
        content: wizardRoomContents
      },
      {
          subStep: '1.2',
          label: '1.2',
          title: 'Guests',
          contents: {
            label1: 'XXX',
            label2: 'YYY'
          }
        },
        {
          subStep: '1.3',
          label: '1.3',
          title: 'Rewards',
          contents: {
            label1: 'XXX',
            label2: 'YYY'
          }
        },
        {
            subStep: '1.4',
            label: '1.4',
            title: 'Comments',
            contents: {
                label1: 'XXX',
                label2: 'YYY'
            }
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
    title: 'Review',
    subSteps: [
      {
        subStep: '3.1',
        label: '3.1',
        title: 'Summary'
      },
      {
        subStep: '3.2',
        label: '3.2',
        title: 'Progress'
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
