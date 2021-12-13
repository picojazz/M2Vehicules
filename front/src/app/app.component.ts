import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

import { IconSetService } from '@coreui/icons-angular';
import { freeSet } from '@coreui/icons';
import { AuthenticationService } from './_services';
import { User } from './_models';

@Component({
  // tslint:disable-next-line
  selector: 'body',
  template: '<router-outlet></router-outlet>',
  providers: [IconSetService],
})
export class AppComponent implements OnInit {
  currentUser: User;
  constructor(
    private router: Router,
    public iconSet: IconSetService,
    private authenticationService: AuthenticationService
  ) {
    // iconSet singleton
    iconSet.icons = { ...freeSet };
    this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  ngOnInit() {
    this.router.events.subscribe((evt) => {
      if (!(evt instanceof NavigationEnd)) {
        return;
      }
      window.scrollTo(0, 0);
    });
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
}

}
