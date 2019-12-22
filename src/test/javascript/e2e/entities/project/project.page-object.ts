import { element, by, ElementFinder } from 'protractor';

export class ProjectComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-project div table .btn-danger'));
  title = element.all(by.css('jhi-project div h2#page-heading span')).first();

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

export class ProjectUpdatePage {
  pageTitle = element(by.id('jhi-project-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  projectNoInput = element(by.id('field_projectNo'));
  cisProgramTypeInput = element(by.id('field_cisProgramType'));
  projEffDateInput = element(by.id('field_projEffDate'));
  custNoInput = element(by.id('field_custNo'));
  custContactIdInput = element(by.id('field_custContactId'));
  projNameInput = element(by.id('field_projName'));
  projAddressInput = element(by.id('field_projAddress'));
  projAddlAddressInput = element(by.id('field_projAddlAddress'));
  projCityInput = element(by.id('field_projCity'));
  projStateInput = element(by.id('field_projState'));
  projZipCodeInput = element(by.id('field_projZipCode'));
  projCountyInput = element(by.id('field_projCounty'));
  projEstStartDateInput = element(by.id('field_projEstStartDate'));
  projEstCompletionDateInput = element(by.id('field_projEstCompletionDate'));
  projEstDrawdownDateInput = element(by.id('field_projEstDrawdownDate'));
  projCustIntIndInput = element(by.id('field_projCustIntInd'));
  customerSelect = element(by.id('field_customer'));

  async getPageTitle() {
    return this.pageTitle.getText();
  }

  async setProjectNoInput(projectNo) {
    await this.projectNoInput.sendKeys(projectNo);
  }

  async getProjectNoInput() {
    return await this.projectNoInput.getAttribute('value');
  }

  async setCisProgramTypeInput(cisProgramType) {
    await this.cisProgramTypeInput.sendKeys(cisProgramType);
  }

  async getCisProgramTypeInput() {
    return await this.cisProgramTypeInput.getAttribute('value');
  }

  async setProjEffDateInput(projEffDate) {
    await this.projEffDateInput.sendKeys(projEffDate);
  }

  async getProjEffDateInput() {
    return await this.projEffDateInput.getAttribute('value');
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

  async setProjNameInput(projName) {
    await this.projNameInput.sendKeys(projName);
  }

  async getProjNameInput() {
    return await this.projNameInput.getAttribute('value');
  }

  async setProjAddressInput(projAddress) {
    await this.projAddressInput.sendKeys(projAddress);
  }

  async getProjAddressInput() {
    return await this.projAddressInput.getAttribute('value');
  }

  async setProjAddlAddressInput(projAddlAddress) {
    await this.projAddlAddressInput.sendKeys(projAddlAddress);
  }

  async getProjAddlAddressInput() {
    return await this.projAddlAddressInput.getAttribute('value');
  }

  async setProjCityInput(projCity) {
    await this.projCityInput.sendKeys(projCity);
  }

  async getProjCityInput() {
    return await this.projCityInput.getAttribute('value');
  }

  async setProjStateInput(projState) {
    await this.projStateInput.sendKeys(projState);
  }

  async getProjStateInput() {
    return await this.projStateInput.getAttribute('value');
  }

  async setProjZipCodeInput(projZipCode) {
    await this.projZipCodeInput.sendKeys(projZipCode);
  }

  async getProjZipCodeInput() {
    return await this.projZipCodeInput.getAttribute('value');
  }

  async setProjCountyInput(projCounty) {
    await this.projCountyInput.sendKeys(projCounty);
  }

  async getProjCountyInput() {
    return await this.projCountyInput.getAttribute('value');
  }

  async setProjEstStartDateInput(projEstStartDate) {
    await this.projEstStartDateInput.sendKeys(projEstStartDate);
  }

  async getProjEstStartDateInput() {
    return await this.projEstStartDateInput.getAttribute('value');
  }

  async setProjEstCompletionDateInput(projEstCompletionDate) {
    await this.projEstCompletionDateInput.sendKeys(projEstCompletionDate);
  }

  async getProjEstCompletionDateInput() {
    return await this.projEstCompletionDateInput.getAttribute('value');
  }

  async setProjEstDrawdownDateInput(projEstDrawdownDate) {
    await this.projEstDrawdownDateInput.sendKeys(projEstDrawdownDate);
  }

  async getProjEstDrawdownDateInput() {
    return await this.projEstDrawdownDateInput.getAttribute('value');
  }

  async setProjCustIntIndInput(projCustIntInd) {
    await this.projCustIntIndInput.sendKeys(projCustIntInd);
  }

  async getProjCustIntIndInput() {
    return await this.projCustIntIndInput.getAttribute('value');
  }

  async customerSelectLastOption() {
    await this.customerSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async customerSelectOption(option) {
    await this.customerSelect.sendKeys(option);
  }

  getCustomerSelect(): ElementFinder {
    return this.customerSelect;
  }

  async getCustomerSelectedOption() {
    return await this.customerSelect.element(by.css('option:checked')).getText();
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

export class ProjectDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-project-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-project'));

  async getDialogTitle() {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}
