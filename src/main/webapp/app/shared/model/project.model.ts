import { Moment } from 'moment';
import { ICustomer } from 'app/shared/model/customer.model';

export interface IProject {
  id?: number;
  projectNo?: string;
  cisProgramType?: string;
  projEffDate?: Moment;
  custNo?: string;
  custContactId?: string;
  projName?: string;
  projAddress?: string;
  projAddlAddress?: string;
  projCity?: string;
  projState?: string;
  projZipCode?: string;
  projCounty?: string;
  projEstStartDate?: Moment;
  projEstCompletionDate?: Moment;
  projEstDrawdownDate?: Moment;
  projCustIntInd?: string;
  customer?: ICustomer;
}

export class Project implements IProject {
  constructor(
    public id?: number,
    public projectNo?: string,
    public cisProgramType?: string,
    public projEffDate?: Moment,
    public custNo?: string,
    public custContactId?: string,
    public projName?: string,
    public projAddress?: string,
    public projAddlAddress?: string,
    public projCity?: string,
    public projState?: string,
    public projZipCode?: string,
    public projCounty?: string,
    public projEstStartDate?: Moment,
    public projEstCompletionDate?: Moment,
    public projEstDrawdownDate?: Moment,
    public projCustIntInd?: string,
    public customer?: ICustomer
  ) {}
}
