import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CiProjectSharedModule } from 'app/shared/shared.module';
import { CustomerComponent } from './customer.component';
import { CustomerDetailComponent } from './customer-detail.component';
import { CustomerUpdateComponent } from './customer-update.component';
import { CustomerDeleteDialogComponent } from './customer-delete-dialog.component';
import { customerRoute } from './customer.route';

@NgModule({
  imports: [CiProjectSharedModule, RouterModule.forChild(customerRoute)],
  declarations: [CustomerComponent, CustomerDetailComponent, CustomerUpdateComponent, CustomerDeleteDialogComponent],
  entryComponents: [CustomerDeleteDialogComponent]
})
export class CiProjectCustomerModule {}
