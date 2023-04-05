package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner (System.in);
		
		System.out.println("Enter whith file directory : ");
		
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List <Employee> list = new ArrayList<>();
			
			String ps = br.readLine();
			
			while(ps != null) {
				
				String [] fields = ps.split(",");
				
				String name = fields[0];
				String email = fields[1];
				double salary = Double.parseDouble(fields[2]);
				
				list.add (new Employee(name, email, salary));
				
				ps = br.readLine();
				
			}
			
			System.out.println("Enter whith salary: ");
			double salary = sc.nextDouble();
	
			
			List <String> ordem = list.stream()
					.filter(s -> s.getSalary() > salary)
					.map(e -> e.getEmail())
					.sorted()
					.collect(Collectors.toList());
					
			System.out.println("Email of people whose salary is more than, " + String.format("%.2f", salary + ":"));
			ordem.forEach(System.out :: println);
					
			double sum = list.stream()
					.filter(p -> p.getName().charAt(0) == 'T')
					.map(p -> p.getSalary())
					.reduce(0.0, (x,y) -> x+y);
			
			System.out.println("Sum of salary from people whose name starts with 'M': " + String.format("%.2f", sum));
			
			
		}
		
		catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		sc.close();
	}

}
