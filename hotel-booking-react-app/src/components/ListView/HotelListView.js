import * as React from 'react';
import PropTypes from 'prop-types';
import $ from 'jquery';
import {Modal, Wizard, Button, Icon} from 'patternfly-react';

import { mockWizardItems } from '../Wizard/__mocks__/mockWizardItems';

import {
  ModalWizardManager,
  ModalWizardSource
} from '../Wizard/ModalWizardManager';

class HotelListView extends React.Component {
    constructor() {
        super();
        this.state = {
            showModal: false
        };
    }
  componentDidMount() {
    this.bindExpand();
  }

  componentDidUpdate() {
    this.unbind();
    this.bindExpand();
  }

  componentWillUnmount(){
    this.unbind();
  }

  bindExpand() {
    // click the list-view heading then expand a row
    $(".list-group-item-header").click(function(event){
      if(!$(event.target).is("button, a, input, .fa-ellipsis-v")){
        $(this).find(".fa-angle-right").toggleClass("fa-angle-down")
          .end().parent().toggleClass("list-view-pf-expand-active")
          .find(".list-group-item-container").toggleClass("hidden");
      }
    });

    // click the close button, hide the expand row and remove the active status
    $(".list-group-item-container .close").on("click", function (){
      $(this).parent().addClass("hidden")
        .parent().removeClass("list-view-pf-expand-active")
        .find(".fa-angle-right").removeClass("fa-angle-down");
    });
  }

  unbind() {
    $(".list-group-item-header").off('click');
    $(".list-group-item-container .close").off('click');
  }

  render() {
        const { hotels } = this.props;
        const { showModal, activeStepIndex, activeSubStepIndex } = this.state;

        return (
          <div className="list-group list-view-pf list-view-pf-view">

            {hotels.map((hotel,i) =>
            <div className="list-group-item" key={i}>

              <div className="list-group-item-header">
                <div className="list-view-pf-expand">
                  <span className="fa fa-angle-right"></span>
              </div>
              <div className="list-view-pf-actions">
                <ModalWizardManager steps={mockWizardItems} hotel={hotel} />
              </div>
              <div className="list-view-pf-main-info">
                <div className="list-view-pf-left">
                  <span className="fa fa-plane list-view-pf-icon-sm"></span>
                </div>
                <div className="list-view-pf-body">
                  <div className="list-view-pf-description">
                    <div className="list-group-item-heading">
                      { hotel.name }
                    </div>
                  </div>
                  <div className="list-view-pf-additional-info">
                    <div className="list-view-pf-additional-info-item">
                      <span className="glyphicon glyphicon-star-empty"></span>
                      <strong>{hotel.stars}</strong>
                    </div>
                    <div className="list-view-pf-additional-info-item">
                      <span className="glyphicon glyphicon-euro"></span>
                      <strong>{hotel.price}</strong>
                    </div>
                    <div className="list-view-pf-additional-info-item">
                      <span className="glyphicon glyphicon-bed"></span>
                      <strong>{hotel.available_rooms}</strong>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div className="list-group-item-container container-fluid hidden">
              <div className="close">
                <span className="pficon pficon-close"></span>
              </div>
              <div className="row">
                <div className="col-md-3">
                </div>
                <div className="col-md-9">
                  <dl className="dl-horizontal">
                    <dt>Address</dt>
                    <dd>{hotel.address}</dd>
                    <dt>City</dt>
                    <dd>{hotel.city_name}</dd>
                    <dt>Country</dt>
                    <dd>{hotel.country_name}</dd>
                  </dl>
                  <p>
                    { hotel.description }
                  </p>
                </div>
              </div>
            </div>
          </div>
          )}

          </div>


//     <Modal
//                    show={showModal}
//                    onHide={this.closeBookingModal}
//                    dialogClassName="modal-lg wizard-pf"
//                  >
//                    <Wizard>
//                      <Modal.Header>
//                        <button
//                          className="close"
//                          onClick={this.closeBookingModal}
//                          aria-hidden="true"
//                          aria-label="Close"
//                        >
//                          <Icon type="pf" name="close" />
//                        </button>
//                        <Modal.Title>Wizard Title</Modal.Title>
//                      </Modal.Header>
//                      <Modal.Body className="wizard-pf-body clearfix">
//                        <Wizard.Steps
//                          steps={renderWizardSteps(
//                            mockWizardItems,
//                            activeStepIndex,
//                            activeSubStepIndex,
//                            this.onStepClick
//                          )}
//                        />
//                        <Wizard.Row>
//                          <Wizard.Sidebar
//                            items={renderSidebarItems(
//                              mockWizardItems,
//                              activeStepIndex,
//                              activeSubStepIndex,
//                              this.onSidebarItemClick
//                            )}
//                          />
//                          <Wizard.Main>
//                            {renderWizardContents(
//                              mockWizardItems,
//                              activeStepIndex,
//                              activeSubStepIndex
//                            )}
//                          </Wizard.Main>
//                        </Wizard.Row>
//                      </Modal.Body>
//                      <Modal.Footer className="wizard-pf-footer">
//                        <Button
//                          bsStyle="default"
//                          className="btn-cancel"
//                          onClick={this.close}
//                        >
//                          Cancel
//                        </Button>
//                        <Button
//                          bsStyle="default"
//                          disabled={activeStepIndex === 0 && activeSubStepIndex === 0}
//                          onClick={this.onBackButtonClick}
//                        >
//                          <Icon type="fa" name="angle-left" />Back
//                        </Button>
//                        {(activeStepIndex === 0 || activeStepIndex === 1) && (
//                          <Button bsStyle="primary" onClick={this.onNextButtonClick}>
//                            Next<Icon type="fa" name="angle-right" />
//                          </Button>
//                        )}
//                        {activeStepIndex === 2 &&
//                          activeSubStepIndex === 0 && (
//                            <Button bsStyle="primary" onClick={this.onNextButtonClick}>
//                              Deploy<Icon type="fa" name="angle-right" />
//                            </Button>
//                          )}
//                        {activeStepIndex === 2 &&
//                          activeSubStepIndex === 1 && (
//                            <Button bsStyle="primary" onClick={this.close}>
//                              Close<Icon type="fa" name="angle-right" />
//                            </Button>
//                          )}
//                      </Modal.Footer>
//                    </Wizard>
//                  </Modal>

        );
  }

}

export default HotelListView;