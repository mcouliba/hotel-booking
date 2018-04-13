import React from 'react';
import WizardBase from './WizardBase';
import { bindMethods } from '../../common/helpers';
import {Button, Icon, Modal, Wizard } from 'patternfly-react';
import constants from '../../core/constants';

import { mockWizardItems } from './__mocks__/mockWizardItems';

import {
  renderWizardSteps,
  renderSidebarItems,
  renderWizardContents
} from './__mocks__/mockWizardRenderers';

export class ModalWizardManager extends WizardBase {
  constructor(props) {
    super(props);
    bindMethods(this, ['open', 'close']);
  }

    async findRoomByHotel() {
        const { hotel } = this.props;
        const response = await fetch(constants.rooms_by_hotel_url + "?hotelId=" + hotel.id)
          .catch(e => console.log("Error when finding rooms by hotel"));
        const json = await response.json();
        await this.setState({rooms : json.content});
    };

  async open() {
    this.setState({ showModal: true });
    await this.findRoomByHotel();
  }

  close() {
    this.setState({ showModal: false });
  }

  render() {
    const { hotel } = this.props;
    const { showModal, activeStepIndex, activeSubStepIndex } = this.state;

    return (
      <div>
        <Button bsStyle="primary" bsSize="large" onClick={this.open}>
          Book!
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
              <Modal.Title>Booking Process for "{hotel.name}"</Modal.Title>
            </Modal.Header>
            <Modal.Body className="wizard-pf-body clearfix">
              <Wizard.Steps
                steps={renderWizardSteps(
                  mockWizardItems,
                  activeStepIndex,
                  activeSubStepIndex,
                  this.onStepClick
                )}
              />
              <Wizard.Row>
                <Wizard.Sidebar
                  items={renderSidebarItems(
                    mockWizardItems,
                    activeStepIndex,
                    activeSubStepIndex,
                    this.onSidebarItemClick
                  )}
                />
                <Wizard.Main>
                  {renderWizardContents(
                    mockWizardItems,
                    activeStepIndex,
                    activeSubStepIndex,
                    this.state.rooms
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
                mockWizardItems,
                activeStepIndex,
                activeSubStepIndex,
                this.onSidebarItemClick,
              )}
            />
            <Wizard.Main>
              {renderWizardContents(
                mockWizardItems,
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
