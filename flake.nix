{
  description = "PyTorch application and development environment";
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixos-unstable";
  };
  outputs = { nixpkgs, ... }:
    let
      system = "x86_64-linux";  # Adjust for your system
      pkgs = import nixpkgs { inherit system; };
      dependencies = with pkgs; [
        maven
      ];
    in {
      devShells."${system}".default = pkgs.mkShell {
        packages = dependencies;
      };
    };
}
