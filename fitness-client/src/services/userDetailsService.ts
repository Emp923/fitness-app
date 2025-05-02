import UserDetails from "../pages/UserDetails";

export type UserDetails = {
  userId?: number,
  preferredName: string,
  availability: string,
  birthday: string | null,
  gender: string,
  restrictions?: string,
  goals?: string,
  comments?: string
};

export const userDetailsSubmit = async (userDetails: UserDetails, token: string): Promise<UserDetails> => {
  const res = await fetch("/api/user-details", {
    method: "POST",
    headers: {
      "Authorization": `Bearer ${token}`,
      "Content-Type": "application/json"
    },
    body: JSON.stringify(userDetails)
  });
  return res.json();
};

export const getUserDetails = async (token: string): Promise<UserDetails[]> => {
  const res = await fetch("/api/user-details", {
    method: "GET",
    headers: {
      "Authorization": `Bearer ${token}`
    },
  });
  return res.json();
};

export const getUserDetailsByUserId = async (userId: number, token: string): Promise<UserDetails> => {
  const res = await fetch(`/api/user-details/${userId}`, {
    method: "GET",
    headers: {
      "Authorization": `Bearer ${token}`,
    },
  });
  return res.json();
};