import { element, by, ElementFinder } from 'protractor';

export class CustomerComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-customer div table .btn-danger'));
  title = element.all(by.css('jhi-customer div h2#page-heading span')).first();

  async clickOnCreateButton() {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton() {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons() {
    return this.deleteButtons.count();
  }

  async getTitle() {
    return this.title.getText();
  }
}

export class CustomerUpdatePage {
  pageTitle = element(by.id('jhi-customer-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  custNoInput = element(by.id('field_custNo'));
  custContactIdInput = element(by.id('field_custContactId'));
  custContactLastNameInput = element(by.id('field_custContactLastName'));
  custContactFirstNameInput = element(by.id('field_custContactFirstName'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setCustNoInput(custNo) {
    await this.custNoInput.sendKeys(custNo);
  }

  async getCustNoInput() {
    return await this.custNoInput.getAttribute('value');
  }

  async setCustContactIdInput(custContactId) {
    await this.custContactIdInput.sendKeys(custContactId);
  }

  async getCustContactIdInput() {
    return await this.custContactIdInput.getAttribute('value');
  }

  async setCustContactLastNameInput(custContactLastName) {
    await this.custContactLastNameInput.sendKeys(custContactLastName);
  }

  async getCustContactLastNameInput() {
    return await this.custContactLastNameInput.getAttribute('value');
  }

  async setCustContactFirstNameInput(custContactFirstName) {
    await this.custContactFirstNameInput.sendKeys(custContactFirstName);
  }

  async getCustContactFirstNameInput() {
    return await this.custContactFirstNameInput.getAttribute('value');
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class CustomerDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-customer-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-customer'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
