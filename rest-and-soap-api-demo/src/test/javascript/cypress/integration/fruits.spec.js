/// <reference types="Cypress" />

context('Fruits', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000');
  });

  it('has a fruit selection box', () => {
    // https://on.cypress.io/type
    cy.get('select#fruit').should('exist');
  });

  it('has an amount selection box', () => {
    cy.get('select#units').should('exist');
  });

  it('has a submit button', () => {
    cy.get('button#ok').should('exist');
  });

  it('should display correct price when submit button is clicked', () => {
    cy.server();
    cy.route('/priceinfoNOK/1/apple', 'fixture:1-apple.json');
    cy.get('select#fruit').select('apple');
    cy.get('select#units').select('1');
    cy.get('button#ok').click();
    cy.get('#price').should('have.text', '3.15');
  })
});
