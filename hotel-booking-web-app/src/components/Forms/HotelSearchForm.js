import * as React from 'react';
import PropTypes from 'prop-types';
import { Tooltip, OverlayTrigger } from 'patternfly-react';
import { HotelSearch } from '../../models/HotelSearch';

const HotelSearchForm = props => {
  const cityNameTooltip = (
    <Tooltip id="tooltip">
      <div>City where looking for</div>
    </Tooltip>
  );

  const dateInTooltip = (
      <Tooltip id="tooltip">
        <div>Date In</div>
      </Tooltip>
    );

  const dateOutTooltip = (
      <Tooltip id="tooltip">
        <div>Date Out</div>
      </Tooltip>
    );

  return (
    <form>
      <div className="form-group">
        <label htmlFor="exampleInputName">
          City Name
          <OverlayTrigger
            overlay={cityNameTooltip}
            placement="top"
            trigger="hover focus"
            rootClose
          >
            <span
              id="project_name_icon"
              className="pficon pficon-info form-pficon-info"
            />
          </OverlayTrigger>
        </label>
        <input
          type="text"
          className="form-control"
          id="exampleInputName"
          value={props.value.city_name}
          onChange={(e: any) => {
            props.handleChange(e, 'city_name');
          }}
        />
      </div>
      <div className="form-group">
          <label htmlFor="exampleInputName">
            Date In
            <OverlayTrigger
              overlay={dateInTooltip}
              placement="top"
              trigger="hover focus"
              rootClose
            >
              <span
                id="project_name_icon"
                className="pficon pficon-info form-pficon-info"
              />
            </OverlayTrigger>
          </label>

          <input
            type="date"
            className="form-control"
            id="exampleInputName"
            value={props.value.date_in}
            onChange={(e: any) => {
              props.handleChange(e, 'date_in');
            }}
          />
        </div>
      <div className="form-group">
        <label htmlFor="exampleInputName">
          Date Out
          <OverlayTrigger
            overlay={dateOutTooltip}
            placement="top"
            trigger="hover focus"
            rootClose
          >
            <span
              id="project_name_icon"
              className="pficon pficon-info form-pficon-info"
            />
          </OverlayTrigger>
        </label>

        <input
          type="date"
          className="form-control"
          id="exampleInputName"
          value={props.value.date_out}
          onChange={(e: any) => {
            props.handleChange(e, 'date_out');
          }}
        />
      </div>
      <button
        id="cancelProjectButton"
        type="submit"
        className="btn btn-default"
        onClick={(e: any) => {
          props.handleSubmit(e);
        }}
      >
        Cancel
      </button>
      &nbsp;&nbsp;
      <button
        type="submit"
        className="btn btn-primary"
        onClick={(e: any) => {
          props.handleSubmit(e);
        }}
      >
        Submit
      </button>
    </form>
  );
};

HotelSearchForm.propTypes = {
  handleSubmit: PropTypes.func,
  value: HotelSearch
};

export default HotelSearchForm;
