export interface ICustomer {
  id?: number;
  custNo?: string;
  custContactId?: string;
  custContactLastName?: string;
  custContactFirstName?: string;
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public custNo?: string,
    public custContactId?: string,
    public custContactLastName?: string,
    public custContactFirstName?: string
  ) {}
}
