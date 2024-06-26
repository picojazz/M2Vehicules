import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Containers
import { DefaultLayoutComponent } from './containers';
import { ConnexionComponent } from './front/connexion/connexion.component';
import { HomeComponent } from './front/home/home.component';
import { InscriptionclientComponent } from './front/inscriptionclient/inscriptionclient.component';
import { ModiprofileComponent } from './front/modiprofile/modiprofile.component';
import { ReservationComponent } from './front/reservation/reservation.component';
import { TestComponent } from './front/test/test.component';

import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { AuthGuard } from './_helpers';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'M2Vehicule/homee',
    pathMatch: 'full',
  },
  {
    path: '404',
    component: P404Component,
    data: {
      title: 'Page 404'
    }
  },
  {
    path: '500',
    component: P500Component,
    data: {
      title: 'Page 500'
    }
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  
  {
    path: 'register',
    component: RegisterComponent,
    data: {
      title: 'Register Page'
    }
  },
  {
    path: 'connexion',
    component: ConnexionComponent,
    data: {
      title: 'connexion Page'
    }
  },
  {
    path: 'inscriptionclient',
    component: InscriptionclientComponent,
    data: {
      title: 'connexion Inscription'
    }
  },
  {
    path: 'modiprofile',
    component: ModiprofileComponent,
    data: {
      title: 'page modification profile'
    }
  },
  {
  
    path: 'M2Vehicule',
    canActivate: [AuthGuard],
    component: DefaultLayoutComponent,
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'homee',
        component: HomeComponent,
      },
     

      {
        path: 'test',
        component: TestComponent,
      },
      {
        path: 'reservation',
        component: ReservationComponent,
      },





      //template
      {
        path: 'base',
        loadChildren: () => import('./views/base/base.module').then(m => m.BaseModule)
      },
      {
        path: 'buttons',
        loadChildren: () => import('./views/buttons/buttons.module').then(m => m.ButtonsModule)
      },
      {
        path: 'charts',
        loadChildren: () => import('./views/chartjs/chartjs.module').then(m => m.ChartJSModule)
      },
      {
        path: 'dashboard',
        canActivate: [AuthGuard],
        loadChildren: () => import('./views/dashboard/dashboard.module').then(m => m.DashboardModule)
      },
      {
        path: 'icons',
        loadChildren: () => import('./views/icons/icons.module').then(m => m.IconsModule)
      },
      {
        path: 'notifications',
        loadChildren: () => import('./views/notifications/notifications.module').then(m => m.NotificationsModule)
      },
      {
        path: 'theme',
        loadChildren: () => import('./views/theme/theme.module').then(m => m.ThemeModule)
      },
      {
        path: 'widgets',
        loadChildren: () => import('./views/widgets/widgets.module').then(m => m.WidgetsModule)
      }
    ]
  },
  { path: '**', component: P404Component }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' }) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
