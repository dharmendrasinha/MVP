import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProjectComponentsPage, ProjectDeleteDialog, ProjectUpdatePage } from './project.page-object';

const expect = chai.expect;

describe('Project e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let projectComponentsPage: ProjectComponentsPage;
  let projectUpdatePage: ProjectUpdatePage;
  let projectDeleteDialog: ProjectDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Projects', async () => {
    await navBarPage.goToEntity('project');
    projectComponentsPage = new ProjectComponentsPage();
    await browser.wait(ec.visibilityOf(projectComponentsPage.title), 5000);
    expect(await projectComponentsPage.getTitle()).to.eq('Projects');
  });

  it('should load create Project page', async () => {
    await projectComponentsPage.clickOnCreateButton();
    projectUpdatePage = new ProjectUpdatePage();
    expect(await projectUpdatePage.getPageTitle()).to.eq('Create or edit a Project');
    await projectUpdatePage.cancel();
  });

  it('should create and save Projects', async () => {
    const nbButtonsBeforeCreate = await projectComponentsPage.countDeleteButtons();

    await projectComponentsPage.clickOnCreateButton();
    await promise.all([
      projectUpdatePage.setProjectNoInput('projectNo'),
      projectUpdatePage.setCisProgramTypeInput('cisProgramType'),
      projectUpdatePage.setProjEffDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      projectUpdatePage.setCustNoInput('custNo'),
      projectUpdatePage.setCustContactIdInput('custContactId'),
      projectUpdatePage.setProjNameInput('projName'),
      projectUpdatePage.setProjAddressInput('projAddress'),
      projectUpdatePage.setProjAddlAddressInput('projAddlAddress'),
      projectUpdatePage.setProjCityInput('projCity'),
      projectUpdatePage.setProjStateInput('projState'),
      projectUpdatePage.setProjZipCodeInput('projZipCode'),
      projectUpdatePage.setProjCountyInput('projCounty'),
      projectUpdatePage.setProjEstStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      projectUpdatePage.setProjEstCompletionDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      projectUpdatePage.setProjEstDrawdownDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      projectUpdatePage.setProjCustIntIndInput('projCustIntInd'),
      projectUpdatePage.customerSelectLastOption()
    ]);
    expect(await projectUpdatePage.getProjectNoInput()).to.eq('projectNo', 'Expected ProjectNo value to be equals to projectNo');
    expect(await projectUpdatePage.getCisProgramTypeInput()).to.eq(
      'cisProgramType',
      'Expected CisProgramType value to be equals to cisProgramType'
    );
    expect(await projectUpdatePage.getProjEffDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected projEffDate value to be equals to 2000-12-31'
    );
    expect(await projectUpdatePage.getCustNoInput()).to.eq('custNo', 'Expected CustNo value to be equals to custNo');
    expect(await projectUpdatePage.getCustContactIdInput()).to.eq(
      'custContactId',
      'Expected CustContactId value to be equals to custContactId'
    );
    expect(await projectUpdatePage.getProjNameInput()).to.eq('projName', 'Expected ProjName value to be equals to projName');
    expect(await projectUpdatePage.getProjAddressInput()).to.eq('projAddress', 'Expected ProjAddress value to be equals to projAddress');
    expect(await projectUpdatePage.getProjAddlAddressInput()).to.eq(
      'projAddlAddress',
      'Expected ProjAddlAddress value to be equals to projAddlAddress'
    );
    expect(await projectUpdatePage.getProjCityInput()).to.eq('projCity', 'Expected ProjCity value to be equals to projCity');
    expect(await projectUpdatePage.getProjStateInput()).to.eq('projState', 'Expected ProjState value to be equals to projState');
    expect(await projectUpdatePage.getProjZipCodeInput()).to.eq('projZipCode', 'Expected ProjZipCode value to be equals to projZipCode');
    expect(await projectUpdatePage.getProjCountyInput()).to.eq('projCounty', 'Expected ProjCounty value to be equals to projCounty');
    expect(await projectUpdatePage.getProjEstStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected projEstStartDate value to be equals to 2000-12-31'
    );
    expect(await projectUpdatePage.getProjEstCompletionDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected projEstCompletionDate value to be equals to 2000-12-31'
    );
    expect(await projectUpdatePage.getProjEstDrawdownDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected projEstDrawdownDate value to be equals to 2000-12-31'
    );
    expect(await projectUpdatePage.getProjCustIntIndInput()).to.eq(
      'projCustIntInd',
      'Expected ProjCustIntInd value to be equals to projCustIntInd'
    );
    await projectUpdatePage.save();
    expect(await projectUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await projectComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Project', async () => {
    const nbButtonsBeforeDelete = await projectComponentsPage.countDeleteButtons();
    await projectComponentsPage.clickOnLastDeleteButton();

    projectDeleteDialog = new ProjectDeleteDialog();
    expect(await projectDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Project?');
    await projectDeleteDialog.clickOnConfirmButton();

    expect(await projectComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
