import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProject } from 'app/shared/model/project.model';

type EntityResponseType = HttpResponse<IProject>;
type EntityArrayResponseType = HttpResponse<IProject[]>;

@Injectable({ providedIn: 'root' })
export class ProjectService {
  public resourceUrl = SERVER_API_URL + 'api/projects';

  constructor(protected http: HttpClient) {}

  create(project: IProject): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(project);
    return this.http
      .post<IProject>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(project: IProject): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(project);
    return this.http
      .put<IProject>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProject>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProject[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(project: IProject): IProject {
    const copy: IProject = Object.assign({}, project, {
      projEffDate: project.projEffDate != null && project.projEffDate.isValid() ? project.projEffDate.toJSON() : null,
      projEstStartDate: project.projEstStartDate != null && project.projEstStartDate.isValid() ? project.projEstStartDate.toJSON() : null,
      projEstCompletionDate:
        project.projEstCompletionDate != null && project.projEstCompletionDate.isValid() ? project.projEstCompletionDate.toJSON() : null,
      projEstDrawdownDate:
        project.projEstDrawdownDate != null && project.projEstDrawdownDate.isValid() ? project.projEstDrawdownDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.projEffDate = res.body.projEffDate != null ? moment(res.body.projEffDate) : null;
      res.body.projEstStartDate = res.body.projEstStartDate != null ? moment(res.body.projEstStartDate) : null;
      res.body.projEstCompletionDate = res.body.projEstCompletionDate != null ? moment(res.body.projEstCompletionDate) : null;
      res.body.projEstDrawdownDate = res.body.projEstDrawdownDate != null ? moment(res.body.projEstDrawdownDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((project: IProject) => {
        project.projEffDate = project.projEffDate != null ? moment(project.projEffDate) : null;
        project.projEstStartDate = project.projEstStartDate != null ? moment(project.projEstStartDate) : null;
        project.projEstCompletionDate = project.projEstCompletionDate != null ? moment(project.projEstCompletionDate) : null;
        project.projEstDrawdownDate = project.projEstDrawdownDate != null ? moment(project.projEstDrawdownDate) : null;
      });
    }
    return res;
  }
}
