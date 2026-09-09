export const seedAuthState = {
  cookies: [],

  origins: [
    {
      origin: "https://qa-commerce-lab.vercel.app",

      localStorage: [
        {
          name: "user",

          value: JSON.stringify({
            id: "seed",
            first: "QA",
            last: "Tester",
            email: "qa.user@example.com",
          }),
        },
      ],
    },
  ],
};
