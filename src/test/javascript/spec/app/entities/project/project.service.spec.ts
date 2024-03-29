import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ProjectService } from 'app/entities/project/project.service';
import { IProject, Project } from 'app/shared/model/project.model';

describe('Service Tests', () => {
  describe('Project Service', () => {
    let injector: TestBed;
    let service: ProjectService;
    let httpMock: HttpTestingController;
    let elemDefault: IProject;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(ProjectService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Project(
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            projEffDate: currentDate.format(DATE_TIME_FORMAT),
            projEstStartDate: currentDate.format(DATE_TIME_FORMAT),
            projEstCompletionDate: currentDate.format(DATE_TIME_FORMAT),
            projEstDrawdownDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Project', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            projEffDate: currentDate.format(DATE_TIME_FORMAT),
            projEstStartDate: currentDate.format(DATE_TIME_FORMAT),
            projEstCompletionDate: currentDate.format(DATE_TIME_FORMAT),
            projEstDrawdownDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            projEffDate: currentDate,
            projEstStartDate: currentDate,
            projEstCompletionDate: currentDate,
            projEstDrawdownDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new Project(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Project', () => {
        const returnedFromService = Object.assign(
          {
            projectNo: 'BBBBBB',
            cisProgramType: 'BBBBBB',
            projEffDate: currentDate.format(DATE_TIME_FORMAT),
            custNo: 'BBBBBB',
            custContactId: 'BBBBBB',
            projName: 'BBBBBB',
            projAddress: 'BBBBBB',
            projAddlAddress: 'BBBBBB',
            projCity: 'BBBBBB',
            projState: 'BBBBBB',
            projZipCode: 'BBBBBB',
            projCounty: 'BBBBBB',
            projEstStartDate: currentDate.format(DATE_TIME_FORMAT),
            projEstCompletionDate: currentDate.format(DATE_TIME_FORMAT),
            projEstDrawdownDate: currentDate.format(DATE_TIME_FORMAT),
            projCustIntInd: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            projEffDate: currentDate,
            projEstStartDate: currentDate,
            projEstCompletionDate: currentDate,
            projEstDrawdownDate: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Project', () => {
        const returnedFromService = Object.assign(
          {
            projectNo: 'BBBBBB',
            cisProgramType: 'BBBBBB',
            projEffDate: currentDate.format(DATE_TIME_FORMAT),
            custNo: 'BBBBBB',
            custContactId: 'BBBBBB',
            projName: 'BBBBBB',
            projAddress: 'BBBBBB',
            projAddlAddress: 'BBBBBB',
            projCity: 'BBBBBB',
            projState: 'BBBBBB',
            projZipCode: 'BBBBBB',
            projCounty: 'BBBBBB',
            projEstStartDate: currentDate.format(DATE_TIME_FORMAT),
            projEstCompletionDate: currentDate.format(DATE_TIME_FORMAT),
            projEstDrawdownDate: currentDate.format(DATE_TIME_FORMAT),
            projCustIntInd: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            projEffDate: currentDate,
            projEstStartDate: currentDate,
            projEstCompletionDate: currentDate,
            projEstDrawdownDate: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Project', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
