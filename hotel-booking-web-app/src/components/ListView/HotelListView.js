import * as React from 'react';
import $ from 'jquery';
import { Button} from 'patternfly-react';
const redhatHotelLogo = require('../../redhat-hotel.png');

class HotelListView extends React.Component {

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

        return (
          <div className="list-group list-view-pf list-view-pf-view">

            {hotels.map((hotel,i) =>
            <div className="list-group-item" key={i}>

              <div className="list-group-item-header">
                <div className="list-view-pf-expand">
                  <span className="fa fa-angle-right"></span>
              </div>
              <div className="list-view-pf-actions">
                <Button
                    bsStyle="primary"
                    bsSize="large"
                    onClick={(e: any) => {
                       this.props.handleSubmit(e, hotel);
                     }}
                 >
                    Select this hotel
                </Button>
              </div>
              <div className="list-view-pf-main-info">
                <div className="list-view-pf-left">
                  <span className="fa fa-building list-view-pf-icon-sm"></span>
                </div>
                <div className="list-view-pf-body">
                  <div className="list-view-pf-description">
                    <div className="list-group-item-heading">
                      { hotel.name }
                    </div>
                  </div>
                  <div className="list-view-pf-additional-info">
                    <div className="list-view-pf-additional-info-item">
                      <span className="fa fa-map-marker"></span>
                      {hotel.address}, {hotel.city} ({hotel.country})
                    </div>
                    <div className="list-view-pf-additional-info-item abtesting hidden">
                      <span className="fa fa-star"></span>
                      {hotel.stars}
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
                <div className="col-md-3 abtesting hidden" >
                      <img style={{ height: 180 }} src={redhatHotelLogo} alt="logo" />
                </div>
                <div className="col-md-9">
                  <dl className="dl-horizontal">
                    <dt>Id</dt>
                    <dd>{hotel.id}</dd>
                    <dt>Stars</dt>
                    <dd>{hotel.stars}</dd>
                    <dt>Email</dt>
                    <dd>{hotel.email}</dd>
                    <dt>URL</dt>
                    <dd>{hotel.url}</dd>
                    <dt>Address</dt>
                    <dd>{hotel.address}</dd>
                    <dt>City</dt>
                    <dd>{hotel.city}</dd>
                    <dt>Postal Code</dt>
                    <dd>{hotel.postal_code}</dd>
                    <dt>Country</dt>
                    <dd>{hotel.country}</dd>
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
        );
  }

}

export default HotelListView;