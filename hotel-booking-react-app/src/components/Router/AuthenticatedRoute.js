import * as React from 'react';
import PropTypes from 'prop-types';
import { Route, Redirect } from 'react-router-dom';
import constants from '../../core/constants';

export const AuthenticatedRoute = ({
  component: C,
  props: cProps,
  ...rest
}) => {
    return (
    <Route
        {...rest}
        render={(props) =>
            cProps.credentials && cProps.credentials.token ? (
            <C {...props} {...cProps} />
            ) : (
            <Redirect to={`/login`} />
        )}
    />
    );
};

AuthenticatedRoute.propTypes = {
  component: PropTypes.any,
  props: PropTypes.object
};
