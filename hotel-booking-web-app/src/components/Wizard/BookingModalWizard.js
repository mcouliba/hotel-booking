import React from 'react';
import WizardBase from './WizardBase';
import { bindMethods } from '../../common/helpers';
import { Redirect } from 'react-router';
import {Button, Icon, Modal, Wizard } from 'patternfly-react';

import { BookingWizardItems } from './BookingWizardItems';

import {
  renderWizardSteps,
  renderWizardContents
} from './BookingWizardRenderers';

export class BookingModalWizard extends WizardBase {
     constructor(props: any) {
        super(props);
        bindMethods(this, ['open', 'close', 'endClose']);
    }

    async open() {
        const { room } = this.props;
        const newBookingState = await this.props.handleBookingState(room);
        await this.setState({ bookingState : newBookingState });
        this.setState({ showModal: true });
    }

    close() {
        this.setState({ showModal: false });
    }

    endClose() {
        this.setState({ redirect: true });
    }

  render() {
    const { room, customer } = this.props;
    const { showModal, activeStepIndex, activeSubStepIndex } = this.state;

    if (this.state.redirect) {
        return <Redirect to='/'/>;
    };

    return (
      <div>
        <Button
            bsStyle="primary"
            bsSize="large"
            onClick={this.open}>
          Choose this room
        </Button>

        <Modal
          show={showModal}
          onHide={this.close}
          dialogClassName="modal-lg wizard-pf"
        >
          <Wizard>
            <Modal.Header>
              <button
                className="close"
                onClick={this.close}
                aria-hidden="true"
                aria-label="Close"
              >
                <Icon type="pf" name="close" />
              </button>
              <Modal.Title>Validation</Modal.Title>
            </Modal.Header>
            <Modal.Body className="wizard-pf-body clearfix">
              <Wizard.Steps
                steps={renderWizardSteps(
                  BookingWizardItems,
                  activeStepIndex,
                  activeSubStepIndex,
                  this.onStepClick
                )}
              />
              <Wizard.Row>
                <Wizard.Main>
                  {renderWizardContents(
                    BookingWizardItems,
                    activeStepIndex,
                    activeSubStepIndex,
                    room,
                    customer
                  )}
                </Wizard.Main>
              </Wizard.Row>
            </Modal.Body>
            <Modal.Footer className="wizard-pf-footer">
                {(activeStepIndex === 0 || activeStepIndex === 1
                    || activeStepIndex === 2) && (
                    <Button
                    bsStyle="default"
                    className="btn-cancel"
                    onClick={this.close}
                    >
                    Cancel
                    </Button>
                )}
                {(activeStepIndex === 0 || activeStepIndex === 1
                || activeStepIndex === 2) && (
                    <Button
                    bsStyle="default"
                    disabled={activeStepIndex === 0 && activeSubStepIndex === 0}
                    onClick={this.onBackButtonClick}
                    >
                    <Icon type="fa" name="angle-left" />Back
                    </Button>
                )}

              {(activeStepIndex === 0) && (
                <Button bsStyle="primary" onClick={this.onNextButtonClick}>
                  Next<Icon type="fa" name="angle-right" />
                </Button>
              )}
              {activeStepIndex === 1 && (
                  <Button bsStyle="success" onClick={this.onBookButtonClick}>
                    Book!<Icon type="fa" name="angle-right" />
                  </Button>
                )}
              {activeStepIndex === 2 && (
                  <Button bsStyle="primary" onClick={this.endClose}>
                    Close
                  </Button>
                )}
            </Modal.Footer>
          </Wizard>
        </Modal>
      </div>
    );
  }
}

export const ModalWizardSource = `
  <div>
    <Button bsStyle="primary" bsSize="large" onClick={this.open}>
      Launch modal wizard
    </Button>

    <Modal
      show={showModal}
      onHide={this.close}
      dialogClassName="modal-lg wizard-pf"
    >
      <Wizard>
        <Modal.Header>
          <button
            className="close"
            onClick={this.close}
            aria-hidden="true"
            aria-label="Close"
          >
            <Icon type="pf" name="close" />
          </button>
          <Modal.Title>Wizard Title</Modal.Title>
        </Modal.Header>
        <Modal.Body className="wizard-pf-body clearfix">
          <Wizard.Steps
            steps={renderWizardSteps(
              mockWizardItems,
              activeStepIndex,
              activeSubStepIndex,
              this.onStepClick,
            )}
          />
          <Wizard.Row>
            <Wizard.Sidebar
              items={renderSidebarItems(
                BookingWizardItems,
                activeStepIndex,
                activeSubStepIndex,
                this.onSidebarItemClick,
              )}
            />
            <Wizard.Main>
              {renderWizardContents(
                BookingWizardItems,
                activeStepIndex,
                activeSubStepIndex,
              )}
            </Wizard.Main>
          </Wizard.Row>
        </Modal.Body>
        <Modal.Footer className="wizard-pf-footer">
          <Button
            bsStyle="default"
            className="btn-cancel"
            onClick={this.close}
          >
            Cancel
          </Button>
          <Button
            bsStyle="default"
            disabled={activeStepIndex === 0 && activeSubStepIndex === 0}
            onClick={this.onBackButtonClick}
          >
            <Icon type="fa" name="angle-left" />Back
          </Button>
          {(activeStepIndex === 0 || activeStepIndex === 1) && (
            <Button bsStyle="primary" onClick={this.onNextButtonClick}>
              Next<Icon type="fa" name="angle-right" />
            </Button>
          )}
          {activeStepIndex === 2 &&
            activeSubStepIndex === 0 && (
              <Button bsStyle="primary" onClick={this.onNextButtonClick}>
                Confirm<Icon type="fa" name="angle-right" />
              </Button>
            )}
          {activeStepIndex === 2 &&
            activeSubStepIndex === 1 && (
              <Button bsStyle="primary" onClick={this.close}>
                Close<Icon type="fa" name="angle-right" />
              </Button>
            )}
        </Modal.Footer>
      </Wizard>
    </Modal>
  </div>
`;
