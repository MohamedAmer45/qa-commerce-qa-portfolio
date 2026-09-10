describe("Products API", () => {
  it("returns first page with pagination metadata", () => {
    cy.request("/api/products").then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.data).to.be.an("array").and.have.length(10);

      expect(response.body.meta).to.deep.equal({
        page: 1,
        limit: 10,
        total: 12,
        totalPages: 2,
      });
    });
  });

  it("returns page 2", () => {
    cy.request("/api/products?page=2&limit=10").then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.meta.page).to.equal(2);

      expect(response.body.data).to.have.length(2);
    });
  });

  it("returns empty data beyond last page", () => {
    cy.request("/api/products?page=3&limit=10").then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.data).to.be.an("array").and.have.length(0);
    });
  });

  it("filters Accessories category", () => {
    cy.request("/api/products?category=Accessories").then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.data).to.have.length(3);

      response.body.data.forEach((product: { category: string }) => {
        expect(product.category).to.equal("Accessories");
      });
    });
  });

  it("filters products that are in stock", () => {
    cy.request("/api/products?inStock=true").then((response) => {
      expect(response.status).to.equal(200);

      response.body.data.forEach((product: { stock: number }) => {
        expect(product.stock).to.be.greaterThan(0);
      });
    });
  });

  it("sorts products by price ascending", () => {
    cy.request("/api/products?sort=price_asc").then((response) => {
      const prices = response.body.data.map(
        (product: { price: number }) => product.price,
      );

      const sorted = [...prices].sort((a, b) => a - b);

      expect(prices).to.deep.equal(sorted);
    });
  });

  it("sorts products by price descending", () => {
    cy.request("/api/products?sort=price_desc").then((response) => {
      const prices = response.body.data.map(
        (product: { price: number }) => product.price,
      );

      const sorted = [...prices].sort((a, b) => b - a);

      expect(prices).to.deep.equal(sorted);
    });
  });

  it("returns a valid product by ID", () => {
    cy.request("/api/product?id=1").then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.data).to.include({
        id: 1,
        name: "ApexBook Pro 14",
        brand: "Apex",
        category: "Laptops",
        price: 1299,
        stock: 12,
      });
    });
  });

  it("returns product with raw decimal price", () => {
    cy.request("/api/product?id=8").then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.data.price).to.equal(19.999);
    });
  });

  it("returns zero-price product correctly", () => {
    cy.request("/api/product?id=7").then((response) => {
      expect(response.status).to.equal(200);

      expect(response.body.data.price).to.equal(0);
    });
  });
});
