export type User = {
  id: number,
  username: string,
  authorities: { name: string }[]
};

export const getUsers = async (token: String): Promise<User[]> => {
  const res = await fetch("/api/users", {
    method: "GET",
    headers: { "Authorization": `Bearer ${token}` }
  });
  return res.json();
};
