import * as React from 'react';
import { Route, Switch } from 'react-router-dom';
import asyncComponent from './components/Router/AsyncComponent';
import { AuthenticatedRoute } from './components/Router/AuthenticatedRoute';
import { UnauthenticatedRoute } from './components/Router/UnauthenticatedRoute';

const importHome = asyncComponent(() => import('./pages/Home'));
const importHotelSearch = asyncComponent(() => import('./pages/HotelSearch'));
const importHotelList = asyncComponent(() => import('./pages/HotelList'));
const importLogin = asyncComponent(() => import('./pages/Login'));
const importNotFound = asyncComponent(() => import('./pages/NotFound'));

type Props = {
  childProps: any
};
export const Routes = (props: Props) => {
  return (
    <Switch>
      <AuthenticatedRoute
        path="/"
        exact
        component={importHome}
        props={props.childProps}
      />
      <UnauthenticatedRoute
        path="/login"
        exact
        component={importLogin}
        props={props.childProps}
      />
      <AuthenticatedRoute
        path="/hotelsearch"
        exact
        component={importHotelSearch}
        props={props.childProps}
      />
      <AuthenticatedRoute
          path="/hotellist"
          exact
          component={importHotelList}
          props={props.childProps}
        />

      {/* Finally, catch all unmatched routes */}
      <Route component={importNotFound} />
    </Switch>
  );
};
