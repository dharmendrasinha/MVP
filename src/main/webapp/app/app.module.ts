import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { CiProjectSharedModule } from 'app/shared/shared.module';
import { CiProjectCoreModule } from 'app/core/core.module';
import { CiProjectAppRoutingModule } from './app-routing.module';
import { CiProjectHomeModule } from './home/home.module';
import { CiProjectEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { JhiMainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    CiProjectSharedModule,
    CiProjectCoreModule,
    CiProjectHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    CiProjectEntityModule,
    CiProjectAppRoutingModule
  ],
  declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [JhiMainComponent]
})
export class CiProjectAppModule {}
