import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IProject, Project } from 'app/shared/model/project.model';
import { ProjectService } from './project.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer/customer.service';

@Component({
  selector: 'jhi-project-update',
  templateUrl: './project-update.component.html'
})
export class ProjectUpdateComponent implements OnInit {
  isSaving: boolean;

  customers: ICustomer[];

  editForm = this.fb.group({
    id: [],
    projectNo: [],
    cisProgramType: [],
    projEffDate: [],
    custNo: [],
    custContactId: [],
    projName: [],
    projAddress: [],
    projAddlAddress: [],
    projCity: [],
    projState: [],
    projZipCode: [],
    projCounty: [],
    projEstStartDate: [],
    projEstCompletionDate: [],
    projEstDrawdownDate: [],
    projCustIntInd: [],
    customer: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected projectService: ProjectService,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ project }) => {
      this.updateForm(project);
    });
    this.customerService.query({ filter: 'project-is-null' }).subscribe(
      (res: HttpResponse<ICustomer[]>) => {
        if (!this.editForm.get('customer').value || !this.editForm.get('customer').value.id) {
          this.customers = res.body;
        } else {
          this.customerService
            .find(this.editForm.get('customer').value.id)
            .subscribe(
              (subRes: HttpResponse<ICustomer>) => (this.customers = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  updateForm(project: IProject) {
    this.editForm.patchValue({
      id: project.id,
      projectNo: project.projectNo,
      cisProgramType: project.cisProgramType,
      projEffDate: project.projEffDate != null ? project.projEffDate.format(DATE_TIME_FORMAT) : null,
      custNo: project.custNo,
      custContactId: project.custContactId,
      projName: project.projName,
      projAddress: project.projAddress,
      projAddlAddress: project.projAddlAddress,
      projCity: project.projCity,
      projState: project.projState,
      projZipCode: project.projZipCode,
      projCounty: project.projCounty,
      projEstStartDate: project.projEstStartDate != null ? project.projEstStartDate.format(DATE_TIME_FORMAT) : null,
      projEstCompletionDate: project.projEstCompletionDate != null ? project.projEstCompletionDate.format(DATE_TIME_FORMAT) : null,
      projEstDrawdownDate: project.projEstDrawdownDate != null ? project.projEstDrawdownDate.format(DATE_TIME_FORMAT) : null,
      projCustIntInd: project.projCustIntInd,
      customer: project.customer
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const project = this.createFromForm();
    if (project.id !== undefined) {
      this.subscribeToSaveResponse(this.projectService.update(project));
    } else {
      this.subscribeToSaveResponse(this.projectService.create(project));
    }
  }

  private createFromForm(): IProject {
    return {
      ...new Project(),
      id: this.editForm.get(['id']).value,
      projectNo: this.editForm.get(['projectNo']).value,
      cisProgramType: this.editForm.get(['cisProgramType']).value,
      projEffDate:
        this.editForm.get(['projEffDate']).value != null ? moment(this.editForm.get(['projEffDate']).value, DATE_TIME_FORMAT) : undefined,
      custNo: this.editForm.get(['custNo']).value,
      custContactId: this.editForm.get(['custContactId']).value,
      projName: this.editForm.get(['projName']).value,
      projAddress: this.editForm.get(['projAddress']).value,
      projAddlAddress: this.editForm.get(['projAddlAddress']).value,
      projCity: this.editForm.get(['projCity']).value,
      projState: this.editForm.get(['projState']).value,
      projZipCode: this.editForm.get(['projZipCode']).value,
      projCounty: this.editForm.get(['projCounty']).value,
      projEstStartDate:
        this.editForm.get(['projEstStartDate']).value != null
          ? moment(this.editForm.get(['projEstStartDate']).value, DATE_TIME_FORMAT)
          : undefined,
      projEstCompletionDate:
        this.editForm.get(['projEstCompletionDate']).value != null
          ? moment(this.editForm.get(['projEstCompletionDate']).value, DATE_TIME_FORMAT)
          : undefined,
      projEstDrawdownDate:
        this.editForm.get(['projEstDrawdownDate']).value != null
          ? moment(this.editForm.get(['projEstDrawdownDate']).value, DATE_TIME_FORMAT)
          : undefined,
      projCustIntInd: this.editForm.get(['projCustIntInd']).value,
      customer: this.editForm.get(['customer']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProject>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackCustomerById(index: number, item: ICustomer) {
    return item.id;
  }
}
