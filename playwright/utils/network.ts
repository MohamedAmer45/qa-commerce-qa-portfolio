import type { Request, Response } from "@playwright/test";

export function isApiRequest(request: Request): boolean {
  const url = new URL(request.url());

  return url.pathname.startsWith("/api/");
}

export function isApiResponse(response: Response): boolean {
  return isApiRequest(response.request());
}

export function getRequestSummary(request: Request) {
  const url = new URL(request.url());

  return {
    method: request.method(),
    url: request.url(),
    path: url.pathname,
    query: Object.fromEntries(url.searchParams.entries()),
  };
}

export function getResponseSummary(response: Response) {
  return {
    url: response.url(),
    status: response.status(),
    statusText: response.statusText(),
    headers: response.headers(),
  };
}
