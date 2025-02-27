export type User = {
  id: number,
  username: string,
  authorities: { name: string }[]
};

export const getUsers = async (token: string): Promise<User[]> => {
  const res = await fetch("/api/users", {
    method: "GET",
    headers: { "Authorization": `Bearer ${token}` }
  });
  return res.json();
};

export const updateUserRole = async (userId: number, role: string, token: string): Promise<void> => {
  await fetch(`/api/users/${userId}/update-role`, {
    method: "PATCH",
    headers: {
      "Authorization": `Bearer ${token}`,
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ role: `ROLE_${role.toUpperCase()}` })
  });
};
